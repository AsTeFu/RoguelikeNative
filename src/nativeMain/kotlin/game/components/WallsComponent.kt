package game.components

import ecs.IComponent
import utility.Vector2


class WallsComponent : IComponent {

    private val walls = mutableSetOf<Vector2>()

    fun addWall(position: Vector2) = walls.add(position)

    fun findWallAtPoint(position: Vector2): Boolean = walls.contains(position)

}
