package hello.advanced.proxy.common.advice

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

class TimeAdvice : MethodInterceptor {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun invoke(invocation: MethodInvocation): Any? {
        log.info("TimeProxy 실행!")

        val stopWatch = StopWatch()
        stopWatch.start()
        val result = invocation.proceed()
        stopWatch.stop()

        log.info("TimeProxy 종료: " + stopWatch.shortSummary())

        return result
    }
}