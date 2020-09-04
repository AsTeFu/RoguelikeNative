package game.components

import ecs.IComponent
import game.systems.RenderSystems.IRenderSystem


class RenderType(private vararg val renderSystems: IRenderSystem) : IComponent {

    var currentSystem: IRenderSystem = renderSystems[0]

    fun changeRenderSystem(index: Int) {
        currentSystem = renderSystems[index]
    }

}
