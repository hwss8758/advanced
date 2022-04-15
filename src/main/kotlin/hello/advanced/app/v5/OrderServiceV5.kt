package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(
    private val repository: OrderRepositoryV5,
    private val template: TraceTemplate
) {
    fun orderItem(itemId: String) {
        template.execute("OrderService.save", object : TraceCallback<Unit> {
            override fun call() {
                repository.save(itemId)
            }
        })
    }
}