package hello.advanced.proxy.config.v3_proxyfactory

import hello.advanced.proxy.app.v2.OrderControllerV2
import hello.advanced.proxy.app.v2.OrderRepositoryV2
import hello.advanced.proxy.app.v2.OrderServiceV2
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
class ProxyFactoryConfigV2 {

    @Bean
    fun logTrace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV2 {
        val controllerImpl = OrderControllerV2(orderService(logTrace))
        val proxyFactory = ProxyFactory(controllerImpl)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        return proxyFactory.proxy as OrderControllerV2
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV2 {
        val serviceImpl = OrderServiceV2(orderRepository(logTrace))
        val proxyFactory = ProxyFactory(serviceImpl)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        return proxyFactory.proxy as OrderServiceV2
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV2 {
        val repositoryImpl = OrderRepositoryV2()
        val proxyFactory = ProxyFactory(repositoryImpl)
        proxyFactory.addAdvisor(getAdvisor(logTrace))
        return proxyFactory.proxy as OrderRepositoryV2
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