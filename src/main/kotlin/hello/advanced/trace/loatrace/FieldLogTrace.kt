package hello.advanced.trace.loatrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import org.slf4j.LoggerFactory

class FieldLogTrace : LogTrace {

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
    }

    private val log = LoggerFactory.getLogger(this::class.java)

    private var traceIdHolder: TraceId? = null

    private fun syncTraceId() {
        traceIdHolder = if (traceIdHolder == null) TraceId()
        else traceIdHolder!!.createNextId()
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0..level) {
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }
        return sb.toString()
    }

    private fun complete(status: TraceStatus?, e: Exception? = null) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status?.startTimeMs!!
        val traceId = status.traceId

        if (e == null) {
            log.info(
                "[{}] {}{} time={}ms",
                traceId.id,
                addSpace(COMPLETE_PREFIX, traceId.level),
                status.message,
                resultTimeMs
            )
        } else {
            log.info(
                "[{}] {}{} time={}ms ex={}",
                traceId.id,
                addSpace(EX_PREFIX, traceId.level),
                status.message,
                resultTimeMs,
                e.toString()
            )
        }
    }

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message);
        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        return complete(status)
    }

    override fun exception(status: TraceStatus, e: Exception) {
        return complete(status, e)
    }
}