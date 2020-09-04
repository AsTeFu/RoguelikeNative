package ecs


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
        systems.forEach { it.preUpdateAll() }
        systems.forEach { it.updateAll() }
        systems.forEach { it.postUpdateAll() }
    }

}
