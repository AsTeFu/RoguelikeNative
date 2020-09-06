package game.inventory.outfit.armors

import game.playerAbility.special.Special
import game.inventory.InventoryItem
import game.inventory.Rarity

class Armor(
    title: String,
    rarity: Rarity,
    cost: Int,
    val type: ArmorType,
    private val protect: Int,
    private val dodge: Int,
    private val special : Special,
) : InventoryItem(title, rarity, cost) {

}