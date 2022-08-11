package hello.advanced.proxy.config.v4_postprocessor

import hello.advanced.config.AppV1Config
import hello.advanced.config.AppV2Config
import hello.advanced.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.advanced.proxy.config.v4_postprocessor.postprocessor.PackageLogTraceProxyPostProcessor
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(*[AppV1Config::class, AppV2Config::class])
class BeanPostProcessorConfig {

    @Bean
    fun logTrace() = ThreadLocalLogTrace()

    @Bean
    fun logTraceProxyPostProcessor(logTrace: LogTrace): PackageLogTraceProxyPostProcessor {
        return PackageLogTraceProxyPostProcessor(
            "hello.advanced.proxy.app",
            getAdvisor(logTrace)
        )
    }

    private fun getAdvisor(logTrace: LogTrace): Advisor {
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames(
            "request*",
            "order*",
            "save*"
        )

        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }
}