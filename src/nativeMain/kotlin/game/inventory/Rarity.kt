package game.inventory

import utility.Color
import utility.Colors

private val list = listOf(50, 30, 12, 5, 2, 1)

enum class Rarity(val color: Color, val chance: Int) {
    Usual(Colors.WHITE.color, list[0]),
    Rare(Colors.WHITE.color, list[1]),
    Epic(Colors.WHITE.color, list[2]),
    Legendary(Colors.WHITE.color, list[3]),
    Mythical(Colors.WHITE.color, list[4]),
    Sublime(Colors.WHITE.color, list[5]),
}