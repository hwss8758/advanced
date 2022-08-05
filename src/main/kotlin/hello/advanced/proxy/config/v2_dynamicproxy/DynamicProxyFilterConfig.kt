package hello.advanced.proxy.config.v2_dynamicproxy

import hello.advanced.proxy.app.v1.OrderControllerV1
import hello.advanced.proxy.app.v1.OrderControllerV1Impl
import hello.advanced.proxy.app.v1.OrderRepositoryV1
import hello.advanced.proxy.app.v1.OrderRepositoryV1Impl
import hello.advanced.proxy.app.v1.OrderServiceV1
import hello.advanced.proxy.app.v1.OrderServiceV1Impl
import hello.advanced.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler
import hello.advanced.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class DynamicProxyFilterConfig {

    companion object {
        private val PATTERNS = arrayOf("request*", "order*", "save*")
    }

    @Bean
    fun logTrace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV1 {
        val controllerImpl = OrderControllerV1Impl(orderService(logTrace))
        return Proxy.newProxyInstance(
            OrderControllerV1::class.java.classLoader,
            arrayOf(OrderControllerV1::class.java),
            LogTraceFilterHandler(controllerImpl, logTrace, PATTERNS)
        ) as OrderControllerV1
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV1 {
        val serviceImpl = OrderServiceV1Impl(orderRepository(logTrace))
        return Proxy.newProxyInstance(
            OrderServiceV1::class.java.classLoader,
            arrayOf(OrderServiceV1::class.java),
            LogTraceFilterHandler(serviceImpl, logTrace, PATTERNS)
        ) as OrderServiceV1
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV1 {
        val repositoryImpl = OrderRepositoryV1Impl()
        return Proxy.newProxyInstance(
            OrderRepositoryV1::class.java.classLoader,
            arrayOf(OrderRepositoryV1::class.java),
            LogTraceFilterHandler(repositoryImpl, logTrace, PATTERNS)
        ) as OrderRepositoryV1
    }
}