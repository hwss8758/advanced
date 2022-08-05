package hello.advanced.proxy.common.service

import org.slf4j.LoggerFactory

open class ConcreteService {

    private val log = LoggerFactory.getLogger(this::class.java)

    open fun call() {
        log.info("ConcreteService 호출")
    }
}