package hello.advanced.app.v4

import hello.advanced.trace.loatrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Service

@Service
class OrderServiceV4(
    private val repository: OrderRepositoryV4,
    private val trace: LogTrace
) {

    fun orderItem(itemId: String) {

        val template: AbstractTemplate<Unit> = object : AbstractTemplate<Unit>(trace) {
            override fun call() {
                repository.save(itemId)
            }
        }

        template.execute("OrderServiceV4.save")
    }
}