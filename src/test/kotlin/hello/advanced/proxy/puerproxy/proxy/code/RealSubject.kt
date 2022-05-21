package hello.advanced.proxy.puerproxy.proxy.code

import org.slf4j.LoggerFactory

class RealSubject : Subject {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun operation(): String {
        log.info("실제 객체 호춣")
        Thread.sleep(1000L)
        return "data"
    }
}