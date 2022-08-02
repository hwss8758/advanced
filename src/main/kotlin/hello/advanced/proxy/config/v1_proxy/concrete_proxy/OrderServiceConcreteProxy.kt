package hello.advanced.proxy.config.v1_proxy.concrete_proxy

import hello.advanced.proxy.app.v2.OrderServiceV2
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

class OrderServiceConcreteProxy(private val target: OrderServiceV2, private val logTrace: LogTrace) :
    OrderServiceV2(null) {

    override fun orderItem(itemId: String) {
        var status: TraceStatus? = null

        try {
            status = logTrace.begin("OrderService.orderItem()")
            target.orderItem(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}