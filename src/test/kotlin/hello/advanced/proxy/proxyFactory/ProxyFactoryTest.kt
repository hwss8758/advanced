package hello.advanced.proxy.proxyFactory

import hello.advanced.proxy.common.advice.TimeAdvice
import hello.advanced.proxy.common.service.ServiceImpl
import hello.advanced.proxy.common.service.ServiceInterface
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.AopUtils

class ProxyFactoryTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun interfaceProxy() {
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ServiceInterface

        log.info("targetClass={}", target.javaClass);
        log.info("proxyClass={}", proxy.javaClass);

        proxy.save()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse
    }
}