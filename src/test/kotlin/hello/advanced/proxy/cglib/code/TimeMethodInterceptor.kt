package hello.advanced.proxy.cglib.code

import org.slf4j.LoggerFactory
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch
import java.lang.reflect.Method

class TimeMethodInterceptor(private val target: Any) : MethodInterceptor {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun intercept(obj: Any?, method: Method?, args: Array<out Any>?, proxy: MethodProxy): Any {

        log.info("TimeProxy 실행!")

        val stopWatch = StopWatch()
        stopWatch.start()
        val result = proxy.invoke(target, args)
        stopWatch.stop()

        log.info("TimeProxy 종료: " + stopWatch.shortSummary())

        return result
    }
}