package game.systems.RenderSystems

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
                if (lighting.lighted) graphic.display.color else Color("#777")
        )

//        Terminal.currentLayer = graphic.layer
        Terminal.setLayer(graphic.layer)
        //color
        Terminal.put(lighting.renderPosition, display.graphic)
    }

}
