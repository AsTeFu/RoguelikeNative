package game.systems

import atf.bearlibterminal.*
import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.InputKeyboard
import game.components.baseComponent.Movement
import terminal.Terminal
import utility.Vector2

class InputSystem(engine: Engine) : ISystem(engine) {

    companion object {
        val buttons = mapOf(
                arrayOf(TK_W, TK_UP) to Vector2(0, -1),
                arrayOf(TK_A, TK_LEFT) to Vector2(-1, 0),
                arrayOf(TK_S, TK_DOWN) to Vector2(0, 1),
                arrayOf(TK_D, TK_RIGHT) to Vector2(1, 0)
        )
    }

    override fun filter(entity: Entity): Boolean = entity.hasComponent<InputKeyboard>() and entity.hasComponent<Movement>()

    override fun preUpdate(entity: Entity) {
        val movement = entity.getComponent<Movement>()!!

        movement.direction = Vector2(0, 0)
        buttons.forEach {
            if (Terminal.peek() in it.key)
                movement.direction = buttons.getOrElse(it.key) { Vector2(0, 0) }
        }
    }
}
