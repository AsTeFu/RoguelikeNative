package game.components.inventoryComponent

import ecs.IComponent
import game.inventory.outfit.Weapon

class WeaponComponent(startWeapon: Weapon) : IComponent {

    private var weapon: Weapon = startWeapon

    fun replace(weapon: Weapon): Weapon {
        val oldWeapon = this.weapon
        this.weapon = weapon
        return oldWeapon
    }

//    fun take(weapon: Weapon) : Weapon? {
//        if (this.weapon == null) {
//            this.weapon = weapon
//            return null
//        }
//
//        val oldWeapon = this.weapon
//        this.weapon = weapon
//        return oldWeapon
//    }
//
//    fun takeOff() : Weapon? {
//        val weapon = this.weapon
//        this.weapon = null
//        return weapon
//    }

}