package utility

import kotlinx.serialization.Serializable
import platform.posix.abs
import kotlin.math.sign

class Vector2(val x: Int, val y: Int) {

    val length2 = x * x + y * y

    fun translate(dx: Int, dy: Int): Vector2 = Vector2(x + dx, y + dy)

    fun translate(dPos: Vector2): Vector2 = Vector2(x + dPos.x, y + dPos.y)

    operator fun plus(right: Vector2): Vector2 = Vector2(x + right.x, y + right.y)

    operator fun times(const: Int): Vector2 = Vector2(x * const, y * const)

    operator fun minus(right: Vector2): Vector2 = Vector2(x - right.x, y - right.y)

    fun absArgs(): Vector2 = Vector2(abs(x), abs(y))

    fun signArgs(): Vector2 = Vector2(x.sign, y.sign)

    fun isZero(): Boolean = x == 0 && y == 0
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
//        if (this::class != other?::class) return false

        other as Vector2

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + length2.hashCode()
        return result
    }

    override fun toString(): String {
        return "{$x, $y}"
    }

}

fun Pair<Int, Int>.toVector() = Vector2(this.first, this.second)

