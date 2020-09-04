package game.rooms.builder.creators

import ecs.Engine
import ecs.Entity
import game.components.PointComponent
import game.components.baseComponent.Graphic
import game.components.baseComponent.Lighting
import game.components.baseComponent.Transform
import utility.Color
import utility.Display
import utility.Vector2

abstract class ICreator {

    companion object {
        val pointDisplay = Display('.', Color("#ffffff"))
    }

    abstract fun create(engine: Engine, position: Vector2, display: Display): Entity

    fun createPoint(engine: Engine, position: Vector2, display: Display = pointDisplay): Entity {
        val point = engine.entityManager.createEntity()

        point.addComponent { Transform(position) }
        point.addComponent { Graphic(display) }
        point.addComponent { PointComponent() }
        point.addComponent { Lighting(position) }

        return point
    }

}
