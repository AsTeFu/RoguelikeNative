package game.inventory.generators

import game.playerAbility.special.Special
import game.inventory.Rarity
import game.inventory.outfit.armors.Armor
import game.inventory.outfit.armors.ArmorType
import kotlin.random.Random

class ArmorGenerator {

    private val itemGenerator = ItemGenerator()
    private val itemNamesGenerator = ItemNamesGenerator("")

    fun generateArmor(
        type: ArmorType,
        minRarity: Rarity = Rarity.Usual,
        maxRarity: Rarity = Rarity.Sublime
    ) : Armor {
        val rarity = itemGenerator.getRandomRarity(minRarity, maxRarity)
        val name = itemNamesGenerator.getRandomName(rarity)
        val cost = itemGenerator.getRandomCost(rarity)

        val protect = 10;
        val dodge = 10;
        val special = Special();

        return Armor(name, rarity, cost, type, protect, dodge, special)
    }

    fun generateArmor(
        minRarity: Rarity = Rarity.Usual,
        maxRarity: Rarity = Rarity.Sublime
    ) = generateArmor(ArmorType.values()[Random.nextInt(ArmorType.values().size)], minRarity, maxRarity)

}