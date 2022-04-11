package hello.advanced.trace.threadlocal.code

import org.slf4j.LoggerFactory

class FieldService {

    private val log = LoggerFactory.getLogger(this::class.java)

    private var nameStore: String? = null

    fun logic(name: String): String? {
        log.info("저장 name={} -> nameStore={}", name, nameStore)
        nameStore = name
        Thread.sleep(1000)
        log.info("조회 nameStore={}", nameStore)
        return nameStore
    }
}