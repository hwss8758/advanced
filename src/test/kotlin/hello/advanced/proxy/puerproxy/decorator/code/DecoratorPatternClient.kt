package hello.advanced.proxy.puerproxy.decorator.code

import org.slf4j.LoggerFactory

class DecoratorPatternClient(private val component: Component) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute() {
        val result = component.operation()
        log.info("result = {}", result)
    }
}