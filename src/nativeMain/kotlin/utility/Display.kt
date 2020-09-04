package utility

class Display(val graphic: Char, val color: Color) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Display

        if (graphic != other.graphic) return false
        if (color != other.color) return false

        return true
    }

    override fun hashCode(): Int {
        var result = graphic.hashCode()
        result = 31 * result + color.hashCode()
        return result
    }
}
