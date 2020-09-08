package game.systems.windows

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.windows.WindowComponent
import terminal.Terminal
import utility.Vector2

class WindowSystem(engine: Engine) : ISystem(engine) {

    override fun filter(entity: Entity) = entity.hasComponent<WindowComponent>()

    override fun preUpdate(entity: Entity) {
        val window = entity.getComponent<WindowComponent>()!!
        window?.let { it.preUpdate() }


    }

    override fun postUpdate(entity: Entity) {
        val window = entity.getComponent<WindowComponent>()
        window?.let { it.postUpdate() }
    }

}