package hello.advanced.trace.template

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.loatrace.LogTrace

abstract class AbstractTemplate<T>(private val trace: LogTrace) {

    fun execute(message: String): T {

        var status: TraceStatus? = null

        try {
            status = trace.begin(message)
            val result: T = call()
            trace.end(status)
            return result
        } catch (e: java.lang.Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }

    protected abstract fun call(): T
}

