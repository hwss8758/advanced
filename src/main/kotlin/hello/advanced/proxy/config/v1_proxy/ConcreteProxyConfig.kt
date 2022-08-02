package hello.advanced.proxy.config.v1_proxy

import hello.advanced.proxy.app.v2.OrderControllerV2
import hello.advanced.proxy.app.v2.OrderRepositoryV2
import hello.advanced.proxy.app.v2.OrderServiceV2
import hello.advanced.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import hello.advanced.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import hello.advanced.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteProxyConfig {

    @Bean
    fun logTrace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV2 {
        val controllerImpl = OrderControllerV2(orderService(logTrace))
        return OrderControllerConcreteProxy(controllerImpl, logTrace)
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV2 {
        val serviceImpl = OrderServiceV2(orderRepository(logTrace))
        return OrderServiceConcreteProxy(serviceImpl, logTrace)
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV2 {
        val repositoryImpl = OrderRepositoryV2()
        return OrderRepositoryConcreteProxy(repositoryImpl, logTrace)
    }
}