package hello.advanced.proxy.config.v3_proxyfactory

import hello.advanced.proxy.app.v1.OrderControllerV1
import hello.advanced.proxy.app.v1.OrderControllerV1Impl
import hello.advanced.proxy.app.v1.OrderRepositoryV1
import hello.advanced.proxy.app.v1.OrderRepositoryV1Impl
import hello.advanced.proxy.app.v1.OrderServiceV1
import hello.advanced.proxy.app.v1.OrderServiceV1Impl
import hello.advanced.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV1 {

    @Bean
    fun logTrace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV1 {
        val controllerImpl = OrderControllerV1Impl(orderService(logTrace))
        val proxyFactory = ProxyFactory(controllerImpl)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        return proxyFactory.proxy as OrderControllerV1
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV1 {
        val serviceImpl = OrderServiceV1Impl(orderRepository(logTrace))
        val proxyFactory = ProxyFactory(serviceImpl)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        return proxyFactory.proxy as OrderServiceV1
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV1 {
        val repositoryImpl = OrderRepositoryV1Impl()
        val proxyFactory = ProxyFactory(repositoryImpl)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        return proxyFactory.proxy as OrderRepositoryV1
    }

    private fun getAdvisor(logTrace: LogTrace): Advisor {
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames(
            "request*",
            "order*",
            "save*"
        )

        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }
}