package hello.advanced.proxy.jdkdynamic

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.reflect.Method

class ReflectionTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun reflection0() {
        val target = Hello()

        val callA = target.callA()
        log.info(callA)

        val callB = target.callB()
        log.info(callB)
    }

    @Test
    fun reflection1() {
        val classHello = Class.forName("hello.advanced.proxy.jdkdynamic.ReflectionTest\$Hello")

        val target = Hello()

        val methodCallA = classHello.getMethod("callA")
        val callA = methodCallA.invoke(target)
        log.info(callA.toString())

        val methodCallB = classHello.getMethod("callB")
        val callB = methodCallB.invoke(target)
        log.info(callB.toString())
    }

    @Test
    fun reflection2() {
        val classHello = Class.forName("hello.advanced.proxy.jdkdynamic.ReflectionTest\$Hello")

        val target = Hello()

        val methodCallA = classHello.getMethod("callA")
        dynamicCall(methodCallA, target)

        val methodCallB = classHello.getMethod("callB")
        dynamicCall(methodCallB, target)
    }

    private fun dynamicCall(method: Method, target: Any) {
        val result = method.invoke(target)
        log.info(result.toString())
    }

    class Hello {

        private val log = LoggerFactory.getLogger(this::class.java)

        fun callA(): String {
            log.info("callA")
            return "A"
        }

        fun callB(): String {
            log.info("callB")
            return "B"
        }
    }
}

