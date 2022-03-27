package hello.advanced.trace

import java.util.*

data class TraceId(
    val id: String = createId(),
    val level: Int = 0
) {
    fun createNextId() = TraceId(id, level + 1)

    fun createPreviousId() = TraceId(id, level - 1)

    fun isFirstLevel() = level == 0
}

private fun createId() = UUID.randomUUID().toString().substring(0, 8)
