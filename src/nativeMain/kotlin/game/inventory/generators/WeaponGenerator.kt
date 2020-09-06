package game.inventory.generators

import game.inventory.Rarity
import game.inventory.outfit.Weapon
import kotlin.random.Random

class WeaponGenerator {

    private val itemGenerator = ItemGenerator()
    private val itemNamesGenerator = ItemNamesGenerator("")
    private val listDamages = listOf(
        Pair(20, 40),
        Pair(55, 75),
        Pair(120, 140),
        Pair(200, 230),
        Pair(285, 320),
        Pair(550, 750),
    )

    fun generateWeapon(minRarity: Rarity = Rarity.Usual, maxRarity: Rarity = Rarity.Sublime) : Weapon {
        val rarity = itemGenerator.getRandomRarity(minRarity, maxRarity)
        val name = itemNamesGenerator.getRandomName(rarity)
        val cost = itemGenerator.getRandomCost(rarity)
        val damage = getRandomDamage(rarity)
        val criticalChance = getRandomCriticalChance(rarity)

        return Weapon(name, rarity, cost, damage, criticalChance)
    }

    private fun getRandomDamage(rarity: Rarity) : Int
            = Random.nextInt(listDamages[rarity.ordinal].first, listDamages[rarity.ordinal].second)

    private fun getRandomCriticalChance(rarity: Rarity) : Int
            = 4 + rarity.ordinal * 5;

}