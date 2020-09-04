package game.rooms.builder.creators

import ecs.Engine
import ecs.Entity
import game.components.baseComponent.Graphic
import game.components.baseComponent.Lighting
import game.components.baseComponent.Transform
import utility.Display
import utility.Vector2

class CoinCreator : ICreator() {

    override fun create(engine: Engine, position: Vector2, display: Display): Entity {
//        val point = createPoint(engine, position)

        val coin = engine.entityManager.createEntity()

        coin.addComponent { Transform(position) }
        coin.addComponent { Graphic(display) }
        coin.addComponent { Lighting(position) }

        return coin
    }

}
