package utility

fun brezenham(start: Vector2, end: Vector2, predicate: (Int, Int) -> Boolean): Boolean {
    var d = end - start
    val inc = d.signArgs()

    d = d.absArgs()

    val es: Int
    val el: Int
    val pd: Vector2

    if (d.x > d.y) {
        pd = Vector2(inc.x, 0)
        es = d.y
        el = d.x
    } else {
        pd = Vector2(0, inc.y)
        es = d.x
        el = d.y
    }

    var x = start.x
    var y = start.y
    var err = el / 2

    for (t in 0 until el) {
        err -= es
        if (err < 0) {
            err += el
            x += inc.x
            y += inc.y
        } else {
            x += pd.x
            y += pd.y
        }
        if (predicate(x, y)) return false
    }

    return true
}

