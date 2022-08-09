package hello.advanced.proxy.advisor

import hello.advanced.proxy.common.service.ServiceImpl
import hello.advanced.proxy.common.service.ServiceInterface
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class MultiAdvisorTest {

    @Test
    fun multiAdvisorTest1() {
        // client -> proxy2(advisor2) -> proxy1(advisor1) -> target

        //proxy1 생성
        val target = ServiceImpl()
        val proxyFactory1 = ProxyFactory(target)
        val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        proxyFactory1.addAdvisor(advisor1)
        val proxy1 = proxyFactory1.proxy as ServiceInterface

        //proxy2 생성, target -> proxy1
        val proxyFactory2 = ProxyFactory(proxy1)
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
        proxyFactory2.addAdvisor(advisor2)
        val proxy2 = proxyFactory2.proxy as ServiceInterface

        proxy2.save()
    }

    class Advice1 : MethodInterceptor {
        private val log = LoggerFactory.getLogger(this::class.java)

        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("Advice1 실행!")
            return invocation.proceed()
        }
    }

    class Advice2 : MethodInterceptor {
        private val log = LoggerFactory.getLogger(this::class.java)

        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("Advice2 실행!")
            return invocation.proceed()
        }
    }

    @Test
    fun multiAdvisorTest2() {
        // client -> proxy2(advisor2) -> proxy1(advisor1) -> target
        val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())

        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)

        proxyFactory.addAdvisor(advisor2)
        proxyFactory.addAdvisor(advisor1)

        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
    }
}