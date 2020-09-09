package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.baseComponent.Movement
import game.components.playerComponents.FoodComponent

class StarvationSystem(engine: Engine) : ISystem(engine) {

    override fun filter(entity: Entity) =
        entity.hasComponent<Movement>() and entity.hasComponent<FoodComponent>()

    override fun update(entity: Entity) {
        val foodComponent = entity.getComponent<FoodComponent>()!!
        val movement = entity.getComponent<Movement>()!!

        if (!movement.direction.isZero()) {
            foodComponent.food--
        }
    }

}