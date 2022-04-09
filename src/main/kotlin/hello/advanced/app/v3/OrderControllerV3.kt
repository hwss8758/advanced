package hello.advanced.app.v3

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.loatrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV3(
    private val service: OrderServiceV3,
    private val trace: LogTrace
) {

    @GetMapping("/v3/request")
    fun request(itemId: String): String {

        var status: TraceStatus? = null
        try {
            status = trace.begin("OrderControllerv3.request")
            service.orderItem(itemId)
            trace.end(status)
            return "ok"
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}