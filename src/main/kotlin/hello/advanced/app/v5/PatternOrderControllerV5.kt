package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pattern")
class PatternOrderControllerV5(
    private val service: PatternOrderServiceV5,
    private val template: TraceTemplate
) {

    @GetMapping("/v5/request")
    fun request(itemId: String): String {
        return template.execute("OrderController.request", object : TraceCallback<String> {
            override fun call(): String {
                service.orderItem(itemId)
                return "ok"
            }
        })
    }
}