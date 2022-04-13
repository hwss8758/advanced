package hello.advanced.trace.strategy

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

class ContextV1(val strategy: Strategy) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute() {
        val stopWatch = StopWatch()
        stopWatch.start()

        strategy.call()

        stopWatch.stop()
        log.info("처리시간 : ${stopWatch.totalTimeSeconds}")
    }
}