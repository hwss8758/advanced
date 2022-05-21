package hello.advanced.app.v0

import org.springframework.stereotype.Repository

@Repository
class PatternOrderRepositoryV0 {

    fun save(itemId: String) {
        if (itemId == "ex") throw IllegalStateException("예외 발생!!")
        Thread.sleep(1000L)
    }
}