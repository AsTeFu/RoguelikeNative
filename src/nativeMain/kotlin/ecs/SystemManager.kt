package ecs

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SystemManager(private val engine: Engine) {

    private val systems = arrayListOf<ISystem>()

    fun addSystem(supplierSystem: (Engine) -> ISystem): SystemManager {
        systems.add(supplierSystem(engine))
        return this
    }

    fun setComponents() {
        systems.forEach { it.updateComponents() }
    }

    fun update() {
//        println("===UPDATE===========================\n")
        runBlocking {

//            println("===pre=update====================")

            systems.forEach {
//                println(it)
                it.preUpdateAll()
            }

//            println("===end=pre=update====================\n")
        }
        runBlocking {
//            println("===update====================")

            systems.forEach {
//                println(it)
                it.updateAll()
            }

//            println("===end=update====================\n")
        }
        runBlocking {
//            println("===post=update====================")

            systems.forEach {
//                println(it)
                it.postUpdateAll()
            }

//            println("===end=post=update====================\n")
        }

//        println("===END=UPDATE===========================\n\n\n")
    }

}
