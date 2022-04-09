package hello.advanced.config

import hello.advanced.trace.loatrace.FieldLogTrace
import hello.advanced.trace.loatrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {

    @Bean
    fun logTrace(): LogTrace = FieldLogTrace()
}