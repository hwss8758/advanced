package hello.advanced

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component

@SpringBootTest
class DemoTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var demoComponent: DemoComponent

    @Test
    fun test1() {

        log.info("main start")

        val userA = Runnable { demoComponent.test("hello") }
        val userB = Runnable { demoComponent.test("bye~~") }

        val threadA = Thread(userA).apply { name = "thread-A" }
        val threadB = Thread(userB).apply { name = "thread-B" }

        threadA.start()
        Thread.sleep(1000)
        threadB.start()
        Thread.sleep(10000)

        log.info("main exit")
    }
}


@Component
class DemoComponent {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val testParam: ThreadLocal<String> = ThreadLocal()

    fun test(_testParam: String) {
        log.info("before : testParam = ${testParam.get()}")
        log.info("before : _testParam = $_testParam")

        testParam.set(_testParam)

        Thread.sleep(3000)

        log.info("after : testParam = ${testParam.get()}")
        log.info("after : _testParam = $_testParam")
    }
}