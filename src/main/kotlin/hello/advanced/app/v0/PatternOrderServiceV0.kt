package hello.advanced.app.v0

import org.springframework.stereotype.Service

@Service
class PatternOrderServiceV0(private val repository: PatternOrderRepositoryV0) {

    fun orderItem(itemId: String) {
        repository.save(itemId)
    }
}