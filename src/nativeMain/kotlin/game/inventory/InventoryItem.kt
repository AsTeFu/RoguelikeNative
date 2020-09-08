package game.inventory

abstract class InventoryItem(
    protected val title: String,
    protected val rarity: Rarity,
    protected val cost: Int,
) {

    abstract override fun toString(): String

    open fun toStringExtended(): String = toString()

}