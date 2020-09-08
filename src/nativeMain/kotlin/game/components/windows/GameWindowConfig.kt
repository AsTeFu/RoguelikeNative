package game.components.windows

import sceneManager.scenes.utils.drawBox
import terminal.Terminal
import utility.Vector2

class GameWindowConfig(
    layer: Int,
    position: Vector2,
    size: Vector2,
    title: String,
) : WindowConfig(
    preUpdate = {
        Terminal.setLayer(layer)
        Terminal.crop(position, size)
    },
    postUpdate = {
        Terminal.setLayer(layer)
        drawBox(position, size, title)
    })