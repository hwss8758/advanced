package hello.advanced.proxy.puerproxy.decorator.code

import org.slf4j.LoggerFactory

class RealComponent : Component {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun operation(): String {
        log.info("RealComponent 실행!")
        return "data"
    }
}