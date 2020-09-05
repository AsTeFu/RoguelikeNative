package utility

class Color {

    private var r: Int = 0
    private var g: Int = 0
    private var b: Int = 0
    private var a: Int = 0


    val hex: String
        get() = toHex()
    val alpha: Double
        get() = 255 / 255.0

    constructor(r: Int, g: Int, b: Int, a: Int) {
        this.r = r
        this.g = g
        this.b = b
        this.a = a
    }

    constructor(hex: String) {
        val color = hex.hexToRgb()
        r = color.r
        g = color.g
        b = color.b
        a = color.a
    }

    fun toHex(): String = "#${r.toHex()}${g.toHex()}${b.toHex()}"

    private fun String.hexToRgb(): Color {
        if (!Regex("""^#[0-9A-Fa-f]{6}$""").matches(this)) throw Exception("$this can't parse to color")

        val result = this.substring(1).toInt(16)

        val r = (result shr 16) and 255
        val g = (result shr 8) and 255
        val b = result and 255

        return Color(r, g, b, 255)
    }

    private fun Int.toHex(): String {
        val hex = this.toString(16)
        return if (hex.length > 1) hex else "0$hex"
    }

    fun toInt() : Int = (a shl 24) or (r shl 16) or (g shl 8) or b

}
