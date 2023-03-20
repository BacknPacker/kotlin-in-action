package que.kotlininaction.chapter2

import que.kotlininaction.chapter2.Color.*

enum class Color(
    val r: Int, val g: Int, val b: Int // 상수 프로퍼티 정의
) {
    RED(255,0,0), ORANGE(255,165,0),
    YELLOW(255,255,0), GREEN(0, 255, 0),
    BLUE(0,0,255), INDIGO(75,0,130),
    VIOLET(238,130,238); // 마지막에 반드시 세미콜론

    fun rgb() = (r*256+g)*256+b // enum 클래스 내에 메서드 정의
}

fun getMnemonic(color: Color) =
    when (color) {
        RED -> "Richard"
        ORANGE -> "Of"
        YELLOW -> "York"
        GREEN -> "Gave"
        BLUE -> "Battle"
        INDIGO -> "In"
        VIOLET -> "Vain"
    }

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        else -> throw Exception("Dirty color")
    }

fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
        else -> throw Exception("Dirty color")
    }

fun main() {
    println(BLUE.rgb())
    println(getMnemonic(RED))
}