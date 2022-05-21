package hello.advanced.app.v4

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pattern")
class PatternOrderControllerV4(
    private val service: PatternOrderServiceV4,
    private val trace: LogTrace
) {

    @GetMapping("/v4/request")
    fun request(itemId: String): String {

        val template: AbstractTemplate<String> = object : AbstractTemplate<String>(trace) {
            override fun call(): String {
                service.orderItem(itemId)
                return "ok"
            }
        }

        return template.execute("OrderControllerv4.request")
    }
}