package game.rooms.builder.creators

import ecs.Engine
import ecs.Entity
import game.components.baseComponent.Graphic
import game.components.baseComponent.Lighting
import game.components.baseComponent.Transform
import game.components.baseComponent.Trigger
import game.components.inventoryComponent.PickUpComponent
import game.components.playerComponents.HealthComponent
import utility.Display
import utility.Vector2

class MedKitCreator : ICreator() {

    override fun create(engine: Engine, position: Vector2, display: Display): Entity {
        val point = createPoint(engine, position)

        val medKit = engine.entityManager.createEntity()

        medKit.addComponent { Transform(position) }
        medKit.addComponent { Graphic(display) }
        medKit.addComponent { Lighting(position) }
        medKit.addComponent { PickUpComponent {
            val wallet = it.getComponent<HealthComponent>()!!
            wallet.health += 100
        } }
        medKit.addComponent { Trigger() }

        return medKit
    }

}