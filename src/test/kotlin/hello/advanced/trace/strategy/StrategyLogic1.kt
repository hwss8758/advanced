package hello.advanced.trace.strategy

import org.slf4j.LoggerFactory

class StrategyLogic1 : Strategy {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun call() {
        log.info("비즈니스 로직1 실행")
    }
}