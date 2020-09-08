package sceneManager.context

import ecs.Entity

class ChestDto(
    val player: Entity,
    val chest: Entity,
) : ObjectDto