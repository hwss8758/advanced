package hello.advanced.proxy.puerproxy.concreteproxy.code

import org.slf4j.LoggerFactory

open class ConcreteLogic {

    private val log = LoggerFactory.getLogger(this::class.java)

    open fun operation(): String {
        log.info("ConcreteLogic 실행!!")
        return "data"
    }
}