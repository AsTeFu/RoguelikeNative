package ecs

import kotlin.reflect.KClass

class Entity(val id: Int) {

    val components = hashMapOf<KClass<out IComponent>, IComponent>()

    fun <T : IComponent> addComponent(supplierComponent : () -> T): Entity{
        val component = supplierComponent()
        components[component::class] = component
        return this
    }

    inline fun <reified T : IComponent> getComponent(): T? = components[T::class] as? T

    inline fun <reified T : IComponent> hasComponent() : Boolean = components.containsKey(T::class)

    inline fun <reified T : IComponent> removeComponent() = components.remove(T::class)


}
