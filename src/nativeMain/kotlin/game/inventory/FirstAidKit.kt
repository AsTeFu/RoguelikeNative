package game.inventory

class FirstAidKit(private val value: Int, cost: Int)
    : InventoryItem("First aid kit", Rarity.Usual, cost) {

    override fun toString() = "[color=red]$title:[/color] $value"

}