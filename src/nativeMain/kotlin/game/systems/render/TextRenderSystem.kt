package game.systems.render

import ecs.Entity
import game.components.baseComponent.Graphic
import game.components.baseComponent.Lighting
import terminal.Terminal
import utility.Color
import utility.Display

class TextRenderSystem : IRenderSystem {

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
        Terminal.put(lighting.renderPosition, display.graphic)
    }

}
