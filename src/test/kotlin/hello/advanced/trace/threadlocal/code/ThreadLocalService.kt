package hello.advanced.trace.threadlocal.code

import org.slf4j.LoggerFactory

class ThreadLocalService {

    private val log = LoggerFactory.getLogger(this::class.java)

    private var nameStore: ThreadLocal<String> = ThreadLocal()

    fun logic(name: String): String {
        log.info("저장 name={} -> nameStore={}", name, nameStore.get())

        nameStore.set(name)

        Thread.sleep(1000)

        log.info("조회 nameStore={}", nameStore.get())

        val getNameStore = nameStore.get()

        nameStore.remove()

        return getNameStore
    }
}