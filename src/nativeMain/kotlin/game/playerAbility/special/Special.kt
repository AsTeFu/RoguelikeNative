package game.playerAbility.special

class Special(
    private val points: MutableMap<SpecialStat, Int> = mutableMapOf(),
    private var availablePoints: Int = 0
) {

    private val minPoints = 1
    private val maxPoints = 10

    init {
        SpecialStat.values().forEach {
            points[it] = 0
        }
    }

    fun addPoint(specialStat: SpecialStat) : Boolean {
        if (points[specialStat]!! == maxPoints || availablePoints == 0) return false

        points[specialStat] = points[specialStat]?.plus(1)!!
        availablePoints--

        return true
    }

    fun removePoint(specialStat: SpecialStat) : Boolean {
        if (points[specialStat]!! == minPoints) return false

        points[specialStat] = points[specialStat]?.minus(1)!!
        availablePoints++

        return true
    }

    operator fun get(specialStat: SpecialStat) : Int = points[specialStat]!!

    operator fun plus(right: Special) : Special {
        val newPoints = mutableMapOf<SpecialStat, Int>()
        val availablePoints = this.availablePoints + right.availablePoints

        this.points.forEach { newPoints[it.key] = it.value + (right.points[it.key] ?: 0) }

        return Special(newPoints, availablePoints)
    }

}