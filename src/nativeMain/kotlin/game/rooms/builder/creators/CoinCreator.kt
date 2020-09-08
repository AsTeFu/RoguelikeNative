package game.rooms.builder.creators

import ecs.Engine
import ecs.Entity
import game.components.baseComponent.*
import game.components.inventoryComponent.PickUpComponent
import game.components.playerComponents.WalletComponent
import utility.Display
import utility.Vector2

class CoinCreator : ICreator() {

    override fun create(engine: Engine, position: Vector2, display: Display): Entity {
        val point = createPoint(engine, position)

        val coin = engine.entityManager.createEntity()

        coin.addComponent { Transform(position) }
        coin.addComponent { Graphic(display) }
        coin.addComponent { Lighting(position) }
        coin.addComponent { PickUpComponent {
            val wallet = it.getComponent<WalletComponent>()!!
            wallet.money += 100
        } }
        coin.addComponent { Trigger() }

        return coin
    }

}
