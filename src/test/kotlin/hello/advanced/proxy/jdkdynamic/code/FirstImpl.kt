package hello.advanced.proxy.jdkdynamic.code

import org.slf4j.LoggerFactory

class FirstImpl : FirstInterface {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun call(): String {
        log.info("A 호출")
        return "a"
    }
}