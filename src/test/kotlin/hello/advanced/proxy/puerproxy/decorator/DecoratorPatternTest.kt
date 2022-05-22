package hello.advanced.proxy.puerproxy.decorator

import hello.advanced.proxy.puerproxy.decorator.code.DecoratorPatternClient
import hello.advanced.proxy.puerproxy.decorator.code.MessageDecorator
import hello.advanced.proxy.puerproxy.decorator.code.RealComponent
import hello.advanced.proxy.puerproxy.decorator.code.TimeDecorator
import org.junit.jupiter.api.Test

class DecoratorPatternTest {

    @Test
    fun noDecorator() {
        val realComponent = RealComponent()
        val client = DecoratorPatternClient(realComponent)
        client.execute()
    }

    @Test
    fun decorator1() {
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val client = DecoratorPatternClient(messageDecorator)
        client.execute()
    }

    @Test
    fun decorator2() {
        val realComponent = RealComponent()
        val timeDecorator = TimeDecorator(realComponent)
        val messageDecorator = MessageDecorator(timeDecorator)
        val client = DecoratorPatternClient(messageDecorator)
        client.execute()
    }
}