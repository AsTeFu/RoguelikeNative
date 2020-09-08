package game.inventory.outfit

import game.inventory.InventoryItem
import game.inventory.Rarity

class Ring(title: String, rarity: Rarity, cost: Int) : InventoryItem(title, rarity, cost) {
    override fun toString(): String {
        TODO("Not yet implemented")
    }
}