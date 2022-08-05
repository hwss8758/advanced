package hello.advanced.proxy.cglib

import hello.advanced.proxy.cglib.code.TimeMethodInterceptor
import hello.advanced.proxy.common.service.ConcreteService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.cglib.proxy.Enhancer

class CglibTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun cglib() {
        val target = ConcreteService()

        val enhancer = Enhancer()
        enhancer.setSuperclass(ConcreteService::class.java)
        enhancer.setCallback(TimeMethodInterceptor(target))
        val proxy = enhancer.create() as ConcreteService

        log.info("${target.javaClass}")
        log.info("${proxy.javaClass}")

        proxy.call()
    }
}