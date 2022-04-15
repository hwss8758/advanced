package hello.advanced.trace.callback

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Component

@Component
class TraceTemplate(private val trace: LogTrace) {

    fun <T> execute(message: String, callback: TraceCallback<T>): T {
        var status: TraceStatus? = null

        try {
            status = trace.begin(message)
            val result = callback.call()
            trace.end(status)
            return result
        } catch (e: java.lang.Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}