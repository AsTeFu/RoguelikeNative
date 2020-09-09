package game.rooms.builder.creators

import ecs.Engine
import ecs.Entity
import game.components.baseComponent.Graphic
import game.components.baseComponent.Lighting
import game.components.baseComponent.Transform
import game.components.baseComponent.Trigger
import game.components.inventoryComponent.PickUpComponent
import game.components.playerComponents.FoodComponent
import game.components.playerComponents.WalletComponent
import utility.Display
import utility.Vector2

class FoodCreator : ICreator() {

    override fun create(engine: Engine, position: Vector2, display: Display): Entity {
        val point = createPoint(engine, position)

        val food = engine.entityManager.createEntity()

        food.addComponent { Transform(position) }
        food.addComponent { Graphic(display) }
        food.addComponent { Lighting(position) }
        food.addComponent { PickUpComponent { entity ->
            val foodComponent = entity.getComponent<FoodComponent>()
            foodComponent?.let {
                it.food += 100
            }
        } }
        food.addComponent { Trigger() }

        return food
    }

}