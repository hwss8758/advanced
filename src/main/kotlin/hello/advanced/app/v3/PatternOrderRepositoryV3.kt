package hello.advanced.app.v3

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class PatternOrderRepositoryV3(private val trace: LogTrace) {

    fun save(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = trace.begin("OrderRepositoryv3.save")
            if (itemId == "ex") throw IllegalStateException("예외 발생!!")
            Thread.sleep(1000L)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}