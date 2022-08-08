package hello.advanced.proxy.proxyFactory

import hello.advanced.proxy.common.advice.TimeAdvice
import hello.advanced.proxy.common.service.ConcreteService
import hello.advanced.proxy.common.service.ServiceImpl
import hello.advanced.proxy.common.service.ServiceInterface
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
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

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    fun concreteProxy() {
        val target = ConcreteService()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ConcreteService

        log.info("targetClass={}", target.javaClass);
        log.info("proxyClass={}", proxy.javaClass);

        proxy.call()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
    fun proxyTargetClass() {
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ServiceInterface

        log.info("targetClass={}", target.javaClass);
        log.info("proxyClass={}", proxy.javaClass);

        proxy.save()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue
    }
}