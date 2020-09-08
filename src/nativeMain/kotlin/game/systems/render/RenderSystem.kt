package game.systems.render

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.RenderType
import game.components.baseComponent.Graphic
import game.components.baseComponent.Lighting


class RenderSystem(engine: Engine) : ISystem(engine) {

    override fun filter(entity: Entity): Boolean = entity.hasComponent<RenderType>()

    override fun update(entity: Entity) {
    }

    override fun postUpdate(entity: Entity) {
        val renderSystem = entity.getComponent<RenderType>()?.currentSystem

        engine.entityManager.forEach {
            if (it.hasComponent<Graphic>() && it.hasComponent<Lighting>()) {
                renderSystem?.render(it)
            }
        }
    }
}
