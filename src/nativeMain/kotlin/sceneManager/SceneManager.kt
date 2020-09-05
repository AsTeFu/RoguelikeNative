package sceneManager

import sceneManager.context.Context

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

    fun switchScene(name: String) {
        currentScene.end()
        currentScene = scenes[name] ?: throw Exception("Scene '$name' not found")

        println("Switched scene to $name")

        currentScene.start()
    }

}