package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.baseComponent.Movement
import game.components.playerComponents.StepsComponent
import utility.Vector2

class StepsSystem(engine: Engine) : ISystem(engine) {

    override fun filter(entity: Entity) = entity.hasComponent<StepsComponent>() and entity.hasComponent<Movement>()

    override fun update(entity: Entity) {
        val movement = entity.getComponent<Movement>()!!

        if (movement.direction != Vector2(0, 0)) {
            val steps = entity.getComponent<StepsComponent>()!!
            steps.steps++
        }
    }

}