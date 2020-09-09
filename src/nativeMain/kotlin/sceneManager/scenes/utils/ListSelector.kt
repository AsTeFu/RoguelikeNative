package sceneManager.scenes.utils

import utility.clamp

class ListSelector<T>(val list: List<T>, startIndex: Int = 0) {

    var index: Int = startIndex
        private set

    val currentElement
        get() = list[index]

    fun downward() {
        index = clamp(index + 1, max = list.size - 1)
    }

    fun upward() {
        index = clamp(index - 1, max = list.size - 1)
    }

}