package hello.advanced.trace.strategy

import org.junit.jupiter.api.Test

class ContextV2Test {

    @Test
    fun strategyV1() {
        val contextV2 = ContextV2()
        contextV2.execute(StrategyLogic1())
        contextV2.execute(StrategyLogic2())
    }
}