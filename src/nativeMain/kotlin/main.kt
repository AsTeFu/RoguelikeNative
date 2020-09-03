import atf.bearlibterminal.*
import kotlin.math.round

class Obstacle(var x: Double)

fun toPos(x: Double): Int = round(x).toInt()

fun main() {
    terminal_open()
    terminal_refresh()

    val obstacles = listOf(Obstacle(30.0), Obstacle(55.0), Obstacle(70.0))

    val width = 80
    val groundY = 15

    val speedX = 0.25

    val gravity = 0.2
    var playerY = groundY.toDouble()
    val playerX = 2
    val maxJump = groundY - 4.5
    var speedY = .0
    val maxSpeedY = gravity * 3

    var gameOver = false

    while (true) {
        terminal_clear()

        if (terminal_has_input() != 0) {
            val event = terminal_read()

            if (event == TK_CLOSE) {
                break
            }

            if (!gameOver && event == TK_SPACE && playerY >= groundY) {
                speedY = maxSpeedY
            }
        }

        if (gameOver) {
            terminal_print(1, 1, "Game Over!")
            terminal_refresh()
            continue
        }

        playerY -= speedY
        playerY += gravity

        if (playerY >= groundY) {
            playerY = groundY.toDouble()
        }
        if (playerY <= maxJump) {
            speedY = .0
        }

        terminal_put(playerX, toPos(playerY), '@'.toInt())

        obstacles.forEach {
            it.x -= speedX

            if (it.x <= 0) {
                it.x = width.toDouble()
            }

            if (playerX == toPos(it.x) && toPos(playerY) == groundY) {
                terminal_print(1, 1, "BUM")
                gameOver = true
                // GAME OVER!!
            }

            terminal_put(toPos(it.x), groundY, '#'.toInt())
        }

        repeat(width) {
            terminal_put(it, groundY + 1, '^'.toInt())
        }

        terminal_refresh()
    }

    terminal_close()
}