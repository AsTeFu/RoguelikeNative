package game.inventory

class Wallet(cost: Int) : InventoryItem("Wallet", Rarity.Usual, cost) {

    override fun toString() = "[color=yellow]$title:[/color] $cost"

}