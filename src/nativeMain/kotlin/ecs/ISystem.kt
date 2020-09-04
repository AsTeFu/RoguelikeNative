package ecs

abstract class ISystem(protected val engine: Engine) {

    protected val entities = arrayListOf<Entity>()

    open fun preUpdate(entity: Entity) {}
    open fun update(entity: Entity) {}
    open fun postUpdate(entity: Entity) {}

    fun preUpdateAll() = entities.forEach { preUpdate(it) }
    fun updateAll() = entities.forEach { update(it) }
    fun postUpdateAll() = entities.forEach { postUpdate(it) }

    open fun updateComponents() {
        entities.clear()
        engine.entityManager.entities.values.forEach { if (filter(it)) entities.add(it) }
    }

    abstract fun filter(entity: Entity): Boolean

    fun addEntity(entity: Entity) {
        if (filter(entity)) entities.add(entity)
    }

}
