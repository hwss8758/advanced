package hello.advanced.proxy.config.v1_proxy.concrete_proxy

import hello.advanced.proxy.app.v2.OrderRepositoryV2
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

class OrderRepositoryConcreteProxy(private val target: OrderRepositoryV2, private val logTrace: LogTrace) :
    OrderRepositoryV2() {
    override fun save(itemId: String) {
        var status: TraceStatus? = null

        try {
            status = logTrace.begin("OrderRepository.request()")
            target.save(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}