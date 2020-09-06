package game.inventory

class Food(title: String, cost: Int, private val value: Int)
    : InventoryItem(title, Rarity.Usual, cost) {
}