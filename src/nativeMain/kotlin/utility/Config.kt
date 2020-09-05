package utility

import kotlinx.serialization.*

@Serializable
data class Config (
    val title: String,
    val terminalSize: Pair<Int, Int>
)