package hello.advanced.proxy.puerproxy.proxy

import hello.advanced.proxy.puerproxy.proxy.code.CacheProxy
import hello.advanced.proxy.puerproxy.proxy.code.ProxyPatternClient
import hello.advanced.proxy.puerproxy.proxy.code.RealSubject
import org.junit.jupiter.api.Test

class ProxyPatternTest {

    @Test
    fun noProxyTest() {
        val realSubject = RealSubject()
        val proxyPatternClient = ProxyPatternClient(realSubject)

        proxyPatternClient.execute()
        proxyPatternClient.execute()
        proxyPatternClient.execute()
    }

    @Test
    fun cacheProxyTest() {
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        val client = ProxyPatternClient(cacheProxy)

        client.execute()
        client.execute()
        client.execute()
    }
}