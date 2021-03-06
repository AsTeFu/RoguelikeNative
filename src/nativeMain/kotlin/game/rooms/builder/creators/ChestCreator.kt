package game.rooms.builder.creators

import ecs.Engine
import ecs.Entity
import game.components.baseComponent.*
import game.components.inventoryComponent.ChestComponent
import game.components.inventoryComponent.InventoryComponent
import game.inventory.FirstAidKit
import game.inventory.Food
import game.inventory.Wallet
import game.inventory.generators.ArmorGenerator
import game.inventory.generators.WeaponGenerator
import utility.Display
import utility.Vector2

class ChestCreator : ICreator() {

    override fun create(engine: Engine, position: Vector2, display: Display): Entity {
        val chest = engine.entityManager.createEntity()

        chest.addComponent { Transform(position) }
        chest.addComponent { Graphic(display) }
        chest.addComponent { Lighting(position) }
        chest.addComponent { ChestComponent() }
        chest.addComponent { Collider() }

        val inventory = InventoryComponent(6)
        inventory.addItem(WeaponGenerator().generateWeapon())
        inventory.addItem(ArmorGenerator().generateArmor())
        inventory.addItem(ArmorGenerator().generateArmor())
        inventory.addItem(Wallet(500))
        inventory.addItem(FirstAidKit(500, 100))
        inventory.addItem(Food(100, 200))
        chest.addComponent { inventory }

        return chest
    }

}