package game.components.windows

import ecs.Entity
import game.components.inventoryComponent.WeaponComponent
import sceneManager.scenes.utils.drawBox
import terminal.Terminal
import utility.Vector2

class InfoWindowConfig(
    layer: Int,
    position: Vector2,
    size: Vector2,
    title: String,
    player: Entity,
) : WindowConfig(
    postUpdate = {
        Terminal.setLayer(layer)

        Terminal.clearArea(position, size)
        Terminal.crop(position, size)
        drawBox(position, size, title)

        val drawPosition = position + Vector2(5, 5)
        val weapon = player.getComponent<WeaponComponent>()!!.weapon
    })
