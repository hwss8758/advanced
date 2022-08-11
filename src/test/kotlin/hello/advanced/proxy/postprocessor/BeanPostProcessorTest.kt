package hello.advanced.proxy.postprocessor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


class BeanPostProcessorTest {

    @Test
    fun basicConfig() {
        val applicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)
        val b = applicationContext.getBean("beanA", B::class.java)
        b.helloB()

        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { applicationContext.getBean(A::class.java) }
    }

    @Configuration
    class BasicConfig {
        @Bean(name = ["beanA"])
        fun a(): A = A()

        @Bean
        fun helloPostProcessor(): AToBPostProcessor = AToBPostProcessor()
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

    class AToBPostProcessor : BeanPostProcessor {

        private val log = LoggerFactory.getLogger(this::class.java)

        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
            log.info("beanName={} bean={}", beanName, bean);
            return if (bean is A) B()
            else bean
        }
    }
}