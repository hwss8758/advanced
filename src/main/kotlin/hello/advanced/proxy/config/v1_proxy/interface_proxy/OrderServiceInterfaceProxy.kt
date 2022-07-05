package hello.advanced.proxy.config.v1_proxy.interface_proxy

import hello.advanced.proxy.app.v1.OrderServiceV1
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

class OrderServiceInterfaceProxy(
    private val target: OrderServiceV1,
    private val logTrace: LogTrace
) : OrderServiceV1 {

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