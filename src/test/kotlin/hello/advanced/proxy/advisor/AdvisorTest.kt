package hello.advanced.proxy.advisor

import hello.advanced.proxy.common.advice.TimeAdvice
import hello.advanced.proxy.common.service.ServiceImpl
import hello.advanced.proxy.common.service.ServiceInterface
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.ClassFilter
import org.springframework.aop.MethodMatcher
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import java.lang.reflect.Method

class AdvisorTest {

    @Test
    fun advisorTest1() {
        val target = ServiceImpl()

        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(Pointcut.TRUE, TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()
    }

    @Test
    fun advisorTest2() {
        val target = ServiceImpl()

        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(MyPointcut(), TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()
    }

    class MyPointcut : Pointcut {
        override fun getClassFilter(): ClassFilter {
            return ClassFilter.TRUE
        }

        override fun getMethodMatcher(): MethodMatcher {
            return MyMethodMatcher()
        }
    }

    class MyMethodMatcher : MethodMatcher {

        private val log = LoggerFactory.getLogger(this::class.java)

        companion object {
            private const val MATCH_NAME = "save"
        }

        override fun matches(method: Method, targetClass: Class<*>): Boolean {
            log.info("포인트컷 호출 method={} targetClass={}", method.name, targetClass)
            log.info("포인트컷 결과 {}", method.name == MATCH_NAME)
            return method.name == MATCH_NAME
        }

        override fun matches(method: Method, targetClass: Class<*>, vararg args: Any?): Boolean {
            throw UnsupportedOperationException()
        }

        override fun isRuntime(): Boolean {
            return false
        }
    }

    @Test
    fun advisorTest3() {
        val target = ServiceImpl()

        val proxyFactory = ProxyFactory(target)

        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedName("save")

        val advisor = DefaultPointcutAdvisor(pointcut, TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()
    }
}