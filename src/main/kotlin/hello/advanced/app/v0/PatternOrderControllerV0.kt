package hello.advanced.app.v0

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pattern")
class PatternOrderControllerV0(private val service: PatternOrderServiceV0) {

    @GetMapping("/v0/request")
    fun request(itemId: String): String {
        service.orderItem(itemId)
        return "ok"
    }
}