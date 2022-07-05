package hello.advanced.proxy.config.v1_proxy.interface_proxy

import hello.advanced.proxy.app.v1.OrderRepositoryV1
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

class OrderRepositoryInterfaceProxy(
    private val target: OrderRepositoryV1,
    private val logTrace: LogTrace
) : OrderRepositoryV1 {

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