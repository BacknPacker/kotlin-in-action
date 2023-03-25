package que.kotlininaction.chapter2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import que.kotlininaction.chapter2.ColorTests.Color.*

@SpringBootTest
class ColorTests {
    enum class Color(
        val r: Int, val g: Int, val b: Int // 상수 프로퍼티 정의
    ) {
        RED(255,0,0), ORANGE(255,165,0),
        YELLOW(255,255,0), GREEN(0, 255, 0),
        BLUE(0,0,255), INDIGO(75,0,130),
        VIOLET(238,130,238); // 마지막에 반드시 세미콜론

        fun rgb() = (r*256+g)*256+b // enum 클래스 내에 메서드 정의
    }
    
    val log = LoggerFactory.getLogger(ColorTests::class.java)

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

    @Test
    @DisplayName("Chapter2. Color Tests")
    fun test_color() {
        log.info(BLUE.rgb().toString())
        log.info(getMnemonic(RED))
        assertEquals(255, BLUE.rgb())
        assertEquals("Richard", getMnemonic(RED))
    }
}
