package sceneManager

import sceneManager.context.Context
import sceneManager.scenes.MenuScene

class SceneManager(val context: Context){

    private lateinit var currentScene : IScene
    private val scenes = hashMapOf<String, IScene>()

    fun update() {
        currentScene.update()
    }

    fun addScene(name: String, scene: IScene) {
        if (scenes.isEmpty()) {
            currentScene = scene
            currentScene.start()

            println("Switched scene to $name")
        }

        scenes[name] = scene
    }

    fun addScene(name: SceneNames, scene: IScene) {
        if (scenes.isEmpty()) {
            currentScene = scene
            currentScene.start()

            println("Switched scene to $name")
        }

        scenes[name.title] = scene
    }

    private fun switchScene(name: String) {
        currentScene.end()
        currentScene = scenes[name] ?: throw Exception("Scene '$name' not found")

        println("Switched scene to $name")

        currentScene.start()
    }

    fun switchScene(name: SceneNames) {
        switchScene(name.title)
    }

}