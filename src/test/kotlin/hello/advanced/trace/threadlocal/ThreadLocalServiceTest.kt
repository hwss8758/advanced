package hello.advanced.trace.threadlocal

import hello.advanced.trace.threadlocal.code.ThreadLocalService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ThreadLocalServiceTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val service = ThreadLocalService()

    @Test
    fun field() {

        log.info("main start")

        val userA = Runnable { service.logic("userA") }
        val userB = Runnable { service.logic("userB") }

        val threadA = Thread(userA).apply { name = "thread-A" }
        val threadB = Thread(userB).apply { name = "thread-B" }

        threadA.start()
        Thread.sleep(100)
        threadB.start()
        Thread.sleep(3000)

        log.info("main exit")
    }
}