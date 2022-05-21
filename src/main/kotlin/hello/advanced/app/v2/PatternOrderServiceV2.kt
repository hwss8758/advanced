package hello.advanced.app.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Service

@Service
class PatternOrderServiceV2(
    private val repository: PatternOrderRepositoryV2,
    private val trace: HelloTraceV2
) {

    fun orderItem(itemId1: TraceId, itemId: String) {
        var status: TraceStatus? = null
        try {
            status = trace.beginSync(itemId1, "OrderServiceV2.orderItem")
            repository.save(status.traceId, itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}