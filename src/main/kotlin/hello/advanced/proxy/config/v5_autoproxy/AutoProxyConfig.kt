package hello.advanced.proxy.config.v5_autoproxy

import hello.advanced.config.AppV1Config
import hello.advanced.config.AppV2Config
import hello.advanced.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(*[AppV1Config::class, AppV2Config::class])
class AutoProxyConfig {

    @Bean
    fun logTrace() = ThreadLocalLogTrace()

//    @Bean
    fun advisor1(logTrace: LogTrace): Advisor {
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames(
            "request*",
            "order*",
            "save*"
        )

        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }

//    @Bean
    fun advisor2(logTrace: LogTrace): Advisor {
        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "execution(* hello.advanced.proxy.app..*(..))"

        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }

    @Bean
    fun advisor3(logTrace: LogTrace): Advisor {
        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "execution(* hello.advanced.proxy.app..*(..)) && !execution(* hello.advanced.proxy.app..noLog(..))"

        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }
}