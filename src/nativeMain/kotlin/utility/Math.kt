package utility

fun clamp(value: Int, min: Int = 0, max: Int) : Int {
    if (value < min) return min
    if (value > max) return max
    return value
}