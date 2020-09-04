package game.rooms.builder.creators

import ecs.Engine
import ecs.Entity
import game.components.WallComponent
import game.components.WallsComponent
import game.components.baseComponent.Collider
import game.components.baseComponent.Graphic
import game.components.baseComponent.Lighting
import game.components.baseComponent.Transform
import utility.Display
import utility.Vector2


class WallCreator: ICreator() {

    override fun create(engine: Engine, position: Vector2, display: Display): Entity {
        val wall = engine.entityManager.createEntity()

        wall.addComponent { Transform(position) }
        wall.addComponent { Lighting(position) }
        wall.addComponent { Graphic(display) }
        wall.addComponent { Collider() }
        wall.addComponent { WallComponent() }

        engine.entityManager.getByTag("walls")?.first()?.getComponent<WallsComponent>()?.addWall(position)

        return wall
    }

}
