package hello.advanced.proxy.puerproxy.proxy.code

class ProxyPatternClient(private val subject: Subject) {

    fun execute() {
        subject.operation()
    }
}