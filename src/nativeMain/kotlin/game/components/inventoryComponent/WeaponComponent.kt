package game.components.inventoryComponent

import game.inventory.outfit.Weapon

class WeaponComponent {

    private var weapon: Weapon? = null

    fun take(weapon: Weapon) : Weapon? {
        if (this.weapon == null) {
            this.weapon = weapon
            return null
        }

        val oldWeapon = this.weapon
        this.weapon = weapon
        return oldWeapon
    }

    fun takeOff() : Weapon? {
        val weapon = this.weapon
        this.weapon = null
        return weapon
    }

}