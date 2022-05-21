package hello.advanced.app.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Repository

@Repository
class PatternOrderRepositoryV2(private val trace: HelloTraceV2) {

    fun save(itemId1: TraceId, itemId: String) {
        var status: TraceStatus? = null
        try {
            status = trace.beginSync(itemId1,"OrderRepositoryV2.save")
            if (itemId == "ex") throw IllegalStateException("예외 발생!!")
            Thread.sleep(1000L)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}