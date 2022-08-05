package hello.advanced.proxy.jdkdynamic.code

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(private val target: Any) : InvocationHandler {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {

        log.info("TimeProxy 실행!")

        val stopWatch = StopWatch()
        stopWatch.start()
        val result = method?.invoke(target, *(args ?: emptyArray()))
        stopWatch.stop()

        log.info("TimeProxy 종료: " + stopWatch.shortSummary())

        return result
    }
}