package game.components.inventoryComponent

import ecs.IComponent
import game.inventory.InventoryItem

class InventoryComponent(var capacity: Int) : IComponent {

    private val items = mutableListOf<InventoryItem>()
    val inventory : List<InventoryItem>
        get() = items.toList()

    fun addItem(item: InventoryItem) {
        if (items.size == capacity) return
        items.add(item)
    }

    fun removeItem(index: Int): InventoryItem? {
        return items.removeAt(index)
    }

    fun forEach(action: (InventoryItem) -> Unit) = items.forEach(action)

    operator fun contains(item: InventoryItem) = items.contains(item)

}
