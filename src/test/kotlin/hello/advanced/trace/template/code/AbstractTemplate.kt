package hello.advanced.trace.template.code

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

abstract class AbstractTemplate {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute() {

        val stopWatch = StopWatch()
        stopWatch.start()

        call()

        stopWatch.stop()
        log.info("처리시간 : ${stopWatch.totalTimeSeconds}")
    }

    protected abstract fun call()
}