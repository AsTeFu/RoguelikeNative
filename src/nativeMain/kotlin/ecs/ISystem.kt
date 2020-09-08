package ecs

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class ISystem(protected val engine: Engine) {

    protected val entities = arrayListOf<Entity>()

    open fun preUpdate(entity: Entity) {}
    open fun update(entity: Entity) {}
    open fun postUpdate(entity: Entity) {}

    fun deleteEntity(entity: Entity) {
        entities.remove(entity)
    }

    fun preUpdateAll() {
//        println("\t---pre-update------------")
//        GlobalScope.launch {
            entities.forEach {
                preUpdate(it)
//                println(it)
            }
//        }
//        println("\t---end-pre-update---------")
    }

    fun updateAll() {
//        println("\t---update------------")
//        GlobalScope.launch {
            entities.forEach {
                update(it)
//                println(it)
            }
//        }
//        println("\t---end-update---------")
    }

    fun postUpdateAll() {
//        println("\t---post-update------------")
//        GlobalScope.launch {
            entities.forEach {
                postUpdate(it)
//                println(it)
            }
//        }
//        println("\t---end-post-update---------")
    }

    open fun updateComponents() {
        entities.clear()
        engine.entityManager.entities.values.forEach { if (filter(it)) entities.add(it) }
    }

    abstract fun filter(entity: Entity): Boolean

    fun addEntity(entity: Entity) {
        if (filter(entity)) entities.add(entity)
    }

}
