package hello.advanced

import hello.advanced.proxy.config.v1_proxy.InterfaceProxyConfig
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

//@Import(*[AppV1Config::class, AppV2Config::class])
@Import(InterfaceProxyConfig::class)
@SpringBootApplication(scanBasePackages = ["hello.advanced.proxy.app"])
class AdvancedApplication

fun main(args: Array<String>) {
    runApplication<AdvancedApplication>(*args)
}

