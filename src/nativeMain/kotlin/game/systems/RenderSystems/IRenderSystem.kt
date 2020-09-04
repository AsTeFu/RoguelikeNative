package game.systems.RenderSystems

import ecs.Entity

interface IRenderSystem {

    fun render(entity: Entity)

    fun start() {}

    fun end() {}

}
