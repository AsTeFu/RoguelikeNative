package sceneManager.context

import ecs.Entity
import game.components.inventoryComponent.ArmorComponent
import game.components.inventoryComponent.InventoryComponent
import game.components.inventoryComponent.WeaponComponent

class PlayerDto(
    val entity: Entity
) : ObjectDto {

    val inventoryComponent = entity.getComponent<InventoryComponent>()!!
    val weaponComponent = entity.getComponent<WeaponComponent>()!!
    val armorComponent = entity.getComponent<ArmorComponent>()!!

}