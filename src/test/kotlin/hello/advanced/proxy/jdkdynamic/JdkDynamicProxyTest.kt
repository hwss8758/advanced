package hello.advanced.proxy.jdkdynamic

import hello.advanced.proxy.jdkdynamic.code.FirstImpl
import hello.advanced.proxy.jdkdynamic.code.FirstInterface
import hello.advanced.proxy.jdkdynamic.code.SecondImpl
import hello.advanced.proxy.jdkdynamic.code.SecondInterface
import hello.advanced.proxy.jdkdynamic.code.TimeInvocationHandler
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.reflect.Proxy

class JdkDynamicProxyTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun dynamicA() {
        val target: FirstInterface = FirstImpl()
        val handler = TimeInvocationHandler(target)

        val proxy = Proxy.newProxyInstance(
            FirstInterface::class.java.classLoader,
            arrayOf<Class<*>>(FirstInterface::class.java),
            handler
        ) as FirstInterface

        proxy.call()

        log.info("targetClass={}", target::class.java)
        log.info("proxyClass={}", proxy::class.java)
    }

    @Test
    fun dynamicB() {
        val target: SecondInterface = SecondImpl()
        val handler = TimeInvocationHandler(target)

        val proxy = Proxy.newProxyInstance(
            SecondInterface::class.java.classLoader,
            arrayOf<Class<*>>(SecondInterface::class.java),
            handler
        ) as SecondInterface

        proxy.call()

        log.info("targetClass={}", target::class.java)
        log.info("proxyClass={}", proxy::class.java)
    }
}

