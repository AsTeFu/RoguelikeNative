package sceneManager.context

import ecs.IComponent
import kotlin.reflect.KClass

class Context {

    val dtoObjects = hashMapOf<KClass<out ObjectDto>, ObjectDto>()

    fun <T : ObjectDto> addObject(objectSupplier : () -> T) : Context {
        val dto = objectSupplier()
        dtoObjects[dto::class] = dto
        return this
    }

    inline fun <reified T : ObjectDto> getObject(): T? = dtoObjects[T::class] as? T

}