package ecs

class EntityManager {

    val entities = hashMapOf<Int, Entity>()
    val entitiesByTag = hashMapOf<String, MutableList<Entity>>()

    private var lastId = 1

    fun createEntity(): Entity {
        val id = lastId++
        entities[id] = Entity(id)
        return entities[id]!!
    }

    fun createEntity(tag: String): Entity {
        val entity = createEntity()

        if (tag !in entitiesByTag) entitiesByTag[tag] = arrayListOf(entity)
        else entitiesByTag[tag]!!.add(entity)

        return entity
    }

    fun forEach(func: (Entity) -> Unit) = entities.forEach { (_, v) -> func(v) }

    fun getByTag(tag: String): List<Entity>? = entitiesByTag[tag]?.toList()

}
