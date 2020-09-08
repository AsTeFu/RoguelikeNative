package game.components.windows

import sceneManager.scenes.utils.drawBox
import terminal.Terminal
import utility.Vector2

class InfoWindowConfig(
    layer: Int,
    position: Vector2,
    size: Vector2,
    title: String,
) : WindowConfig(
    postUpdate = {
        Terminal.setLayer(layer)

        Terminal.clearArea(position, size)
        Terminal.crop(position, size)
        drawBox(position, size, title)
    })
