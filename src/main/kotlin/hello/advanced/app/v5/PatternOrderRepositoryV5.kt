package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import org.springframework.stereotype.Repository

@Repository
class PatternOrderRepositoryV5(private val template: TraceTemplate) {

    fun save(itemId: String) {
        template.execute("OrderRepository.save", object : TraceCallback<Unit> {
            override fun call() {
                if (itemId == "ex") throw IllegalStateException("예외 발생!!")
                Thread.sleep(1000L)
            }
        })
    }
}