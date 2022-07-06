package hello.advanced.proxy.puerproxy.concreteproxy.code

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

class TimeProxy(private val realLogic: ConcreteLogic) : ConcreteLogic() {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun operation(): String {
        log.info("TimeDecorator 실행!!")
        val stopWatch = StopWatch()
        stopWatch.start()
        val result = realLogic.operation()
        stopWatch.stop()
        log.info("TimeDecorator 종료 resultTime ${stopWatch.shortSummary()}")
        return result
    }
}