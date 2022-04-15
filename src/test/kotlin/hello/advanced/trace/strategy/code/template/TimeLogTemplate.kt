package hello.advanced.trace.strategy.code.template

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

class TimeLogTemplate {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute(callback: Callback) {
        val stopWatch = StopWatch()
        stopWatch.start()

        callback.call()

        stopWatch.stop()
        log.info("처리시간 : ${stopWatch.totalTimeSeconds}")
    }
}