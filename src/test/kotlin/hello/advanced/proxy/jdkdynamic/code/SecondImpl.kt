package hello.advanced.proxy.jdkdynamic.code

import org.slf4j.LoggerFactory

class SecondImpl : SecondInterface {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun call(): String {
        log.info("B 호출")
        return "b"
    }
}