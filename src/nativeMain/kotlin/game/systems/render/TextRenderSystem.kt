package game.systems.render

import ecs.Engine
import ecs.Entity
import game.components.CameraComponent
import game.components.baseComponent.Graphic
import game.components.baseComponent.Lighting
import terminal.Terminal
import utility.Color
import utility.Display
import utility.Vector2

class TextRenderSystem(private val camera: CameraComponent) : IRenderSystem {

    override fun render(entity: Entity) {
        val lighting = entity.getComponent<Lighting>()!!
        val graphic = entity.getComponent<Graphic>()!!

        if (!lighting.observed) return

        val display = Display(
            graphic.display.graphic,
            if (lighting.lighted) graphic.display.color else Color("#777777")
        )

        Terminal.setLayer(graphic.layer)
        Terminal.setColor(display.color)
        Terminal.put(lighting.renderPosition + Vector2(10, 5) + camera.offset, display.graphic)
    }

}
