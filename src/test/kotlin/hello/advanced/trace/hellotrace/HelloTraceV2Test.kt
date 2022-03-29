package hello.advanced.trace.hellotrace

import org.junit.jupiter.api.Test

internal class HelloTraceV2Test {

    @Test
    fun begin_end_level2() {
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        trace.end(status2)
        trace.end(status1)
    }

    @Test
    fun begin_exception_level2() {
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        trace.exception(status2, IllegalStateException())
        trace.exception(status1, IllegalStateException())
    }
}