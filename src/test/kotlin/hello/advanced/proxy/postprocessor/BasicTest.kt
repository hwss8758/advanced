package hello.advanced.proxy.postprocessor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


class BasicTest {

    @Test
    fun basicConfig() {
        val applicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)
        val a = applicationContext.getBean("beanA", A::class.java)
        a.helloA()

        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { applicationContext.getBean(B::class.java) }
    }

    @Configuration
    class BasicConfig {
        @Bean(name = ["beanA"])
        fun a(): A = A()
    }

    class A {
        private val log = LoggerFactory.getLogger(this::class.java)

        fun helloA() {
            log.info("hello A")
        }
    }

    class B {
        private val log = LoggerFactory.getLogger(this::class.java)

        fun helloB() {
            log.info("hello B")
        }
    }
}