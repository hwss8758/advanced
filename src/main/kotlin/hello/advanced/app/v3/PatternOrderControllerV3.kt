package hello.advanced.app.v3

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pattern")
class PatternOrderControllerV3(
    private val service: PatternOrderServiceV3,
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