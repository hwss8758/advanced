package hello.advanced.trace.threadlocal

import hello.advanced.trace.threadlocal.code.FieldService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class FieldServiceTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val fieldService = FieldService()

    @Test
    fun field() {

        log.info("main start")

        val userA = Runnable { fieldService.logic("userA") }
        val userB = Runnable { fieldService.logic("userB") }

        val threadA = Thread(userA).apply { name = "thread-A" }
        val threadB = Thread(userB).apply { name = "thread-B" }

        threadA.start()
        Thread.sleep(100)
        threadB.start()
        Thread.sleep(3000)

        log.info("main exit")
    }
}