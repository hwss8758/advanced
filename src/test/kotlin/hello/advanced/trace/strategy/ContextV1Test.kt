package hello.advanced.trace.strategy

import hello.advanced.trace.template.code.SubClassLogic1
import hello.advanced.trace.template.code.SubClassLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

class ContextV1Test {
    private val log = LoggerFactory.getLogger(this::class.java)

    private fun logic1() {
        val stopWatch = StopWatch()
        stopWatch.start()

        log.info("ContextV1Test.logic1 : 비지니스 로직 실행")

        stopWatch.stop()
        log.info("처리시간 : ${stopWatch.totalTimeSeconds}")
    }

    private fun logic2() {
        val stopWatch = StopWatch()
        stopWatch.start()

        log.info("ContextV1Test.logic2 : 비지니스 로직 실행")

        stopWatch.stop()
        log.info("처리시간 : ${stopWatch.totalTimeSeconds}")
    }

    @Test
    fun strategyV0() {
        logic1()
        logic2()
    }
}