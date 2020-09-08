package game.components.inventoryComponent

import ecs.IComponent
import game.inventory.InventoryItem

class InventoryComponent(var capacity: Int) : IComponent {

    private val inventory = mutableListOf<InventoryItem>()

    fun addItem(item: InventoryItem) {
        if (inventory.size == capacity) return
        inventory.add(item)
    }

    fun removeItem(index: Int) : InventoryItem? {
        return inventory[index]
    }

}
