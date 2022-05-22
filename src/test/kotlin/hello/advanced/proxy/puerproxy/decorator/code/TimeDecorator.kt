package hello.advanced.proxy.puerproxy.decorator.code

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

class TimeDecorator(private val component: Component) : Component {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun operation(): String {
        log.info("TimeDecorator 실행!!")
        val stopWatch = StopWatch()
        stopWatch.start()
        val result = component.operation()
        stopWatch.stop()
        log.info("TimeDecorator 종료 resultTime ${stopWatch.shortSummary()}")
        return result
    }
}