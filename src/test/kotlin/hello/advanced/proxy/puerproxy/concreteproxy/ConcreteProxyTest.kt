package hello.advanced.proxy.puerproxy.concreteproxy

import hello.advanced.proxy.puerproxy.concreteproxy.code.ConcreteClient
import hello.advanced.proxy.puerproxy.concreteproxy.code.ConcreteLogic
import hello.advanced.proxy.puerproxy.concreteproxy.code.TimeProxy
import org.junit.jupiter.api.Test

class ConcreteProxyTest {

    @Test
    fun noProxy() {
        val concreteLogic = ConcreteLogic()
        val concreteClient = ConcreteClient(concreteLogic)
        concreteClient.execute()
    }

    @Test
    fun addProxy() {
        val concreteLogic = ConcreteLogic()
        val timeProxy = TimeProxy(concreteLogic)
        val concreteClient = ConcreteClient(timeProxy)
        concreteClient.execute()
    }
}