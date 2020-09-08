package game.components.inventoryComponent

import ecs.IComponent
import game.inventory.outfit.armors.Armor
import game.inventory.outfit.armors.ArmorType

class ArmorComponent(private var equipments: MutableMap<ArmorType, Armor>) : IComponent {

    fun putOn(armor: Armor) : Armor? {
        if (armor.type in equipments) {
            val oldArmor = equipments.remove(armor.type)

            equipments[armor.type] = armor

            return oldArmor
        }

        equipments[armor.type] = armor
        return null
    }

    fun takeOff(armorType: ArmorType) = equipments.remove(armorType)

    operator fun Map<ArmorType, Armor>.contains(armor: Armor) : Boolean
            = equipments.containsKey(armor.type)

    operator fun Map<ArmorType, Armor>.contains(armorType: ArmorType) : Boolean
            = equipments.containsKey(armorType)

}