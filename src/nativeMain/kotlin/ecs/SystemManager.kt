package ecs

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import platform.posix.CLOCKS_PER_SEC
import platform.posix.clock


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

        val startTime = clock()
//        println("===UPDATE===========================\n")
//        runBlocking {

//            println("===pre=update====================")

            systems.forEach {
//                println(it)
                it.preUpdateAll()
            }

//            println("===end=pre=update====================\n")
//        }
//        runBlocking {
//            println("===update====================")

            systems.forEach {
//                println(it)
                it.updateAll()
            }

//            println("===end=update====================\n")
//        }
//        runBlocking {
//            println("===post=update====================")

            systems.forEach {
//                println(it)
                it.postUpdateAll()
            }

//            println("===end=post=update====================\n")
//        }

        val endTime = clock()

        println("elapsed time: ${endTime - startTime}")


//        println("===END=UPDATE===========================\n\n\n")
    }

}
