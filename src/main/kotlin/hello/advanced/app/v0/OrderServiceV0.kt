package hello.advanced.app.v0

import org.springframework.stereotype.Service

@Service
class OrderServiceV0(private val repository: OrderRepositoryV0) {

    fun orderItem(itemId: String) {
        repository.save(itemId)
    }
}