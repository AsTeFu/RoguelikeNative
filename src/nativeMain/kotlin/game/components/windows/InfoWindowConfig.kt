package game.components.windows

import ecs.Entity
import game.components.inventoryComponent.WeaponComponent
import game.components.playerComponents.HealthComponent
import game.components.playerComponents.StepsComponent
import game.components.playerComponents.WalletComponent
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

        var drawPosition = position + Vector2(5, 5)
        val weapon = player.getComponent<WeaponComponent>()!!.weapon

        val wallet = player.getComponent<WalletComponent>()!!
        Terminal.print(drawPosition, "Money: ${wallet.money}")
        drawPosition += Vector2(0, 2)

        val health = player.getComponent<HealthComponent>()!!
        Terminal.print(drawPosition, "Health: ${health.health}")
        drawPosition += Vector2(0, 2)

        val steps = player.getComponent<StepsComponent>()!!
        Terminal.print(drawPosition, "Steps: ${steps.steps}")
        drawPosition += Vector2(0, 2)
    })
