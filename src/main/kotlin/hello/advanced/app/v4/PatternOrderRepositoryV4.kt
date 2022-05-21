package hello.advanced.app.v4

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Repository

@Repository
class PatternOrderRepositoryV4(private val trace: LogTrace) {

    fun save(itemId: String) {

        val template: AbstractTemplate<Unit> = object : AbstractTemplate<Unit>(trace) {
            override fun call() {
                if (itemId == "ex") throw IllegalStateException("예외 발생!!")
                Thread.sleep(1000L)
            }
        }

        template.execute("OrderRepositoryv4.save")
    }
}