package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.PointComponent
import game.components.WallComponent
import game.components.WallsComponent
import game.components.baseComponent.Lighting
import game.components.baseComponent.Transform
import terminal.Terminal
import utility.Vector2
import utility.brezenham

class LightingSystem(engine: Engine) : ISystem(engine) {

    private lateinit var player: Entity
    private lateinit var walls: WallsComponent
    private var points: MutableMap<Vector2, Entity> = mutableMapOf()

    override fun updateComponents() {
        super.updateComponents()
        player = engine.entityManager.getByTag("player")?.first()!!
        walls = engine.entityManager.getByTag("walls")?.first()?.getComponent<WallsComponent>()!!
        engine.entityManager.forEach {
            if (it.hasComponent<PointComponent>()) {
                points[it.getComponent<Transform>()?.position!!] = it
            }
        }
    }

    override fun filter(entity: Entity): Boolean = entity.hasComponent<Lighting>()

    override fun update(entity: Entity) {
        if (entity.hasComponent<WallComponent>()) return

        val transform = entity.getComponent<Transform>()!!
        val lighting = entity.getComponent<Lighting>()!!

        val playerTransform = player.getComponent<Transform>()!!

        if (brezenham(playerTransform.position, lighting.renderPosition) { x, y ->
                walls.findWallAtPoint(
                    Vector2(x, y)
                )
            }) {
            lighting.observed = false
        }

        if (brezenham(playerTransform.position, transform.position) { x, y -> walls.findWallAtPoint(Vector2(x, y)) }) {
            lighting.lighted = true
            lighting.observed = true
            lighting.renderPosition = transform.position
        } else {
            if (lighting.observed) {
                lighting.lighted = false
            } else return
        }
    }

    override fun postUpdate(entity: Entity) {
        if (!entity.hasComponent<WallComponent>()) return

        val transform = entity.getComponent<Transform>()!!
        val lighting = entity.getComponent<Lighting>()!!

//        val playerTransform = player?.getComponent<Transform>()!!

        if (isVisibleWall(transform.position)) {
            lighting.lighted = true
            lighting.observed = true
            lighting.renderPosition = transform.position
        } else {
            if (lighting.observed) {
                lighting.lighted = false
            } else return
        }
    }

    private fun isVisibleWall(position: Vector2): Boolean {
//        repeat(3) { i ->
//            repeat(3) { j ->
//                if (i != 0 && j != 0)
//                    if (pick(position.x + i - 1, position.y + j - 1)) return true
//            }
//        }
//        return false
        return pick(position.x + 1, position.y) ||
                pick(position.x - 1, position.y) ||
                pick(position.x, position.y + 1) ||
                pick(position.x, position.y - 1) ||
                pick(position.x + 1, position.y + 1) ||
                pick(position.x + 1, position.y - 1) ||
                pick(position.x - 1, position.y + 1) ||
                pick(position.x - 1, position.y - 1)
    }

    private fun pick(x: Int, y: Int): Boolean {
        if (points.count { Vector2(x, y) == it.key } == 0 || x < 0 || y < 0) return false
        return points[Vector2(x, y)]?.getComponent<Lighting>()?.lighted!! && Terminal.pick(
            Vector2(x, y),
            4
        ) != '#'.toInt()
    }
}
