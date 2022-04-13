package hello.advanced.trace.template

import hello.advanced.trace.template.code.AbstractTemplate
import hello.advanced.trace.template.code.SubClassLogic1
import hello.advanced.trace.template.code.SubClassLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch


class TemplateMethodTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    private fun logic1() {
        val stopWatch = StopWatch()
        stopWatch.start()

        log.info("TemplateMethodTest.logic1 : 비지니스 로직 실행")

        stopWatch.stop()
        log.info("처리시간 : ${stopWatch.totalTimeSeconds}")
    }

    private fun logic2() {
        val stopWatch = StopWatch()
        stopWatch.start()

        log.info("TemplateMethodTest.logic2 : 비지니스 로직 실행")

        stopWatch.stop()
        log.info("처리시간 : ${stopWatch.totalTimeSeconds}")
    }

    @Test
    fun templateMethodV0() {
        logic1()
        logic2()
    }

    @Test
    fun templateMethodV1() {
        val subClassLogic1 = SubClassLogic1()
        subClassLogic1.execute()

        val subClassLogic2 = SubClassLogic2()
        subClassLogic2.execute()
    }

    @Test
    fun templateMethodV2() {

        val template1: AbstractTemplate = object : AbstractTemplate() {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        }

        template1.execute()

        val template2: AbstractTemplate = object : AbstractTemplate() {
            override fun call() {
                log.info("비즈니스 로직2 실행")
            }
        }

        template2.execute()
    }
}