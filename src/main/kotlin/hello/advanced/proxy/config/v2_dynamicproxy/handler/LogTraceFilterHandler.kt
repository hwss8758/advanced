package hello.advanced.proxy.config.v2_dynamicproxy.handler

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceFilterHandler(
    private val target: Any,
    private val logTrace: LogTrace,
    private val patterns: Array<String>
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {

        var status: TraceStatus? = null

        try {
            // method 이름 필터
            val methodName = method.name
            if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
                return method.invoke(target, *(args ?: emptyArray()))
            }

            val message = method.declaringClass?.simpleName + "." + method.name + "()"

            status = logTrace.begin(message)
            val result = method.invoke(target, *(args ?: emptyArray()))
            logTrace.end(status)

            return result
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}