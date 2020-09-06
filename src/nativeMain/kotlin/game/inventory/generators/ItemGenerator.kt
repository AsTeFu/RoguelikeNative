package game.inventory.generators

import game.inventory.Rarity
import utility.clamp
import kotlin.random.Random

class ItemGenerator {

    fun getRandomRarity(minRarity: Rarity = Rarity.Usual, maxRarity: Rarity = Rarity.Sublime) : Rarity {
        val rnd = 100 - Random.nextInt(100)
        val rarity = Rarity.values().findLast { it.chance >= rnd } ?: throw Exception()

        return Rarity.values()[clamp(rarity.ordinal, minRarity.ordinal, maxRarity.ordinal)]
    }

    fun getRandomCost(rarity: Rarity) : Int = Random.nextInt(100 * rarity.ordinal) + 1000


}