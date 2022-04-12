package hello.advanced.config

import hello.advanced.trace.loatrace.LogTrace
import hello.advanced.trace.loatrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {

    @Bean
    fun logTrace(): LogTrace = ThreadLocalLogTrace()
}