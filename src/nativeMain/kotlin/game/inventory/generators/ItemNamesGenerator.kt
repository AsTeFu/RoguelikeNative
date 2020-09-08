package game.inventory.generators

import game.inventory.Rarity
import kotlin.math.sign
import kotlin.random.Random

class ItemNamesGenerator(fileName: String) {

    private lateinit var names : List<List<String>>

    init {
        names = listOf()
    }

    fun getRandomName(rarity: Rarity) : String {
//        val rnd = Random.nextInt(names[rarity.ordinal].size)
//        return names[rarity.ordinal][rnd]
        return "NAME"
    }

}