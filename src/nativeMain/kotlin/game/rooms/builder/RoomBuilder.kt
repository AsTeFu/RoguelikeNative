package game.rooms.builder

import game.components.InputKeyboard
import game.components.RenderType
import game.components.WallsComponent
import game.components.baseComponent.*
import game.rooms.Room
import game.rooms.builder.creators.CoinCreator
import game.rooms.builder.creators.ICreator
import game.rooms.builder.creators.WallCreator
import game.rooms.builder.readers.IRoomReader
import game.rooms.builder.readers.RandomGeneratorRoomReader
import game.rooms.builder.readers.SimpleFileRoomReader
import game.systems.CollisionSystem
import game.systems.InputSystem
import game.systems.LightingSystem
import game.systems.MovementSystem
import game.systems.RenderSystems.RenderSystem
import game.systems.RenderSystems.TextRenderSystem
import utility.Color
import utility.Display
import utility.Vector2


class RoomBuilder() {

    private val roomReader: IRoomReader = SimpleFileRoomReader("/home/astefu/Documents/Roguelike/build/processedResources/native/main/rooms/simpleRooms/room1.rm")

    private val symbolToFunc = hashMapOf<Char, ICreator>()

    init {
        symbolToFunc['$'] = CoinCreator()
        symbolToFunc['#'] = WallCreator()
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

        val render = room.engine.entityManager.createEntity("renderSystem")
        render.addComponent { RenderType(TextRenderSystem()) }

        val walls = room.engine.entityManager.createEntity("walls")
        walls.addComponent { WallsComponent() }

        structure.forEach { display, position ->
            if (display.graphic == '.')
                symbolToFunc['#']?.createPoint(room.engine, position)

            symbolToFunc[display.graphic]?.create(room.engine, position, display)
        }


        room.engine.systemManager.addSystem { e -> InputSystem(e) }
        room.engine.systemManager.addSystem { e -> CollisionSystem(e) }
        room.engine.systemManager.addSystem { e -> MovementSystem(e) }
        room.engine.systemManager.addSystem { e -> LightingSystem(e) }
        room.engine.systemManager.addSystem { e -> RenderSystem(e) }

        room.engine.systemManager.setComponents()

        return room
    }

}
