package ecs

class Engine {

    val entityManager = EntityManager()
    val systemManager = SystemManager(this)

    fun update() = systemManager.update()

    fun deleteEntity(entity: Entity) {
        entityManager.deleteEntity(entity)
        systemManager.deleteEntity(entity)
    }

}
