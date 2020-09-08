package game.inventory.outfit

import game.inventory.InventoryItem
import game.inventory.Rarity
import terminal.Terminal
import utility.Vector2

class Weapon(
    title: String,
    rarity: Rarity,
    cost: Int,
    private val damage: Int,
    private val criticalChance: Int,
) : InventoryItem(title, rarity, cost) {

}
