package hello.advanced.trace.logtrace

import org.junit.jupiter.api.Test

internal class ThreadLocalLogTraceTest {

    private val trace = ThreadLocalLogTrace()

    @Test
    fun test1() {
        val status1 = trace.begin("hello1")
        val status2 = trace.begin("hello2")
        trace.end(status2)
        trace.end(status1)
    }

    @Test
    fun test2() {
        val status1 = trace.begin("hello")
        val status2= trace.begin("hello2")
        trace.exception(status2, IllegalStateException())
        trace.exception(status1, IllegalStateException())
    }
}