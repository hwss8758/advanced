package hello.advanced.proxy.common.service

import org.slf4j.LoggerFactory

class ServiceImpl : ServiceInterface {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun save() {
        log.info("save 호출")
    }

    override fun find() {
        log.info("find 호출")
    }
}