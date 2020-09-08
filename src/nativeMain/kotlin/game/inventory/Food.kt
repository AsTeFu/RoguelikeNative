package game.inventory

class Food(private val value: Int, cost: Int)
    : InventoryItem("Food", Rarity.Usual, cost) {

    override fun toString() = "[color=green]$title:[/color] $value"

}