package hello.advanced.trace.strategy

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

    @Test
    fun strategyV1() {
        val strategyLogic1 = StrategyLogic1()
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = StrategyLogic2()
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV2() {
        val strategyLogic1 = object : Strategy {
            override fun call() {
                log.info("비지니스 로직 1 실행")
            }
        }

        val strategyLogic2 = object : Strategy {
            override fun call() {
                log.info("비지니스 로직 2 실행")
            }
        }

        val context1 = ContextV1(strategyLogic1)
        context1.execute()
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV3() {
        val context1 = ContextV1(object : Strategy {
            override fun call() {
                log.info("비지니스 로직 1 실행")
            }
        })
        context1.execute()

        val context2 = ContextV1(object : Strategy {
            override fun call() {
                log.info("비지니스 로직 2 실행")
            }
        })
        context2.execute()
    }

}