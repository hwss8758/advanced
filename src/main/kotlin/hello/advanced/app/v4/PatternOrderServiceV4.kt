package hello.advanced.app.v4

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Service

@Service
class PatternOrderServiceV4(
    private val repository: PatternOrderRepositoryV4,
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