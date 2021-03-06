package game.rooms.builder

import game.components.CameraComponent
import game.components.InputKeyboard
import game.components.RenderType
import game.components.WallsComponent
import game.components.baseComponent.*
import game.components.inventoryComponent.ArmorComponent
import game.components.inventoryComponent.InventoryComponent
import game.components.inventoryComponent.WeaponComponent
import game.components.playerComponents.FoodComponent
import game.components.playerComponents.HealthComponent
import game.components.playerComponents.StepsComponent
import game.components.playerComponents.WalletComponent
import game.components.windows.GameWindowConfig
import game.components.windows.InfoWindowConfig
import game.components.windows.LoggerWindowConfig
import game.components.windows.WindowComponent
import game.inventory.generators.ArmorGenerator
import game.inventory.generators.WeaponGenerator
import game.inventory.outfit.armors.ArmorType
import game.rooms.Room
import game.rooms.builder.creators.*
import game.rooms.builder.readers.IRoomReader
import game.rooms.builder.readers.SimpleFileRoomReader
import game.systems.*
import game.systems.render.RenderSystem
import game.systems.render.TextRenderSystem
import game.systems.windows.WindowSystem
import sceneManager.SceneManager
import sceneManager.scenes.utils.drawBox
import terminal.Terminal
import utility.Color
import utility.Display
import utility.Vector2


class RoomBuilder(private val sceneManager: SceneManager) {

    private val roomReader: IRoomReader =
        SimpleFileRoomReader("/home/astefu/Documents/Roguelike/build/processedResources/native/main/rooms/simpleRooms/room1.rm")

    private val symbolToFunc = hashMapOf<Char, ICreator>()

    init {
        symbolToFunc['$'] = CoinCreator()
        symbolToFunc['#'] = WallCreator()
        symbolToFunc['+'] = MedKitCreator()
        symbolToFunc['X'] = ChestCreator()
        symbolToFunc['*'] = FoodCreator()
    }

    fun build(): Room {
        val structure = roomReader.read()
        val room = Room(1)

        val player = room.engine.entityManager.createEntity("player")

        player.addComponent { Graphic(Display('@', Color(255, 255, 255, 255)), 2) }
        player.addComponent { Transform(Vector2(10, 10)) }
        player.addComponent { Movement() }
        player.addComponent { Collider() }
        player.addComponent { Lighting(Vector2(10, 10)) }
        player.addComponent { InputKeyboard() }
        player.addComponent { HealthComponent(100) }
        player.addComponent { FoodComponent(100) }
        player.addComponent { StepsComponent() }
        player.addComponent { WalletComponent(300) }
        player.addComponent { InventoryComponent(5) }
        player.addComponent { WeaponComponent(WeaponGenerator().generateWeapon()) }
        player.addComponent {
            ArmorComponent(
                mutableMapOf(
                    ArmorType.Helmet to ArmorGenerator().generateArmor(ArmorType.Helmet),
                    ArmorType.Armour to ArmorGenerator().generateArmor(ArmorType.Armour),
                    ArmorType.Boots to ArmorGenerator().generateArmor(ArmorType.Boots),
                )
            )
        }

        val camera = room.engine.entityManager.createEntity("camera")
        camera.addComponent {
            CameraComponent(Vector2(150 * 60 / 200, 45 * 70 / 200), Vector2(4, 2))
        }
        camera.addComponent { Transform(player.getComponent<Transform>()!!.position) }

        val render = room.engine.entityManager.createEntity("renderSystem")
        render.addComponent { RenderType(TextRenderSystem(camera.getComponent()!!)) }

        val walls = room.engine.entityManager.createEntity("walls")
        walls.addComponent { WallsComponent() }

        structure.forEach { display, position ->
            if (display.graphic == '.')
                symbolToFunc['#']?.createPoint(room.engine, position)

            symbolToFunc[display.graphic]?.create(room.engine, position, display)
        }


        val gameWindow = room.engine.entityManager.createEntity("gameWindow")
        gameWindow.addComponent {
            WindowComponent(GameWindowConfig(20, Vector2(0, 0), Vector2(100, 31), "GAME"))
        }

        val infoWindow = room.engine.entityManager.createEntity("infoWindow")
        infoWindow.addComponent {
            WindowComponent(InfoWindowConfig(21, Vector2(100, 0), Vector2(50, 45), "STAT", player))
        }

        val loggerWindow = room.engine.entityManager.createEntity("loggerWindow")
        loggerWindow.addComponent {
            WindowComponent(LoggerWindowConfig(22, Vector2(0, 30), Vector2(100, 15), "LOGGER"))
        }

        room.engine.systemManager.addSystem { e -> InputSystem(e) }
        room.engine.systemManager.addSystem { e -> CollisionSystem(e) }
        room.engine.systemManager.addSystem { e -> TriggerSystem(e) }
        room.engine.systemManager.addSystem { e -> MovementSystem(e) }
        room.engine.systemManager.addSystem { e -> PickupSystem(e) }
        room.engine.systemManager.addSystem { e -> CameraSystem(e) }
        room.engine.systemManager.addSystem { e -> LightingSystem(e) }
        room.engine.systemManager.addSystem { e -> RenderSystem(e) }
        room.engine.systemManager.addSystem { e -> StepsSystem(e) }
        room.engine.systemManager.addSystem { e -> StarvationSystem(e) }
        room.engine.systemManager.addSystem { e -> WindowSystem(e) }
        room.engine.systemManager.addSystem { e -> ChestOpenSystem(e, sceneManager) }
        room.engine.systemManager.addSystem { e -> InventoryOpenSystem(e, sceneManager) }

        room.engine.systemManager.setComponents()

        return room
    }

}
