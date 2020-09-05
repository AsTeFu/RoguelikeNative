package sceneManager.scenes.utils

import utility.clamp

class ListSelector<T>(private val list: List<T>) {

    private var index: Int = 0

    val currentElement
        get() = list[index]

    fun downward() {
        index = clamp(index + 1, max = list.size - 1)
    }

    fun upward() {
        index = clamp(index - 1, max = list.size - 1)
    }

}