package que.kotlininaction.chapter2

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RectangleTests {
    class Rectangle(val height:Int, val witdh:Int) {
        val isSquare : Boolean
            get(){
                return height == witdh
            }
    }

    val log = LoggerFactory.getLogger(RectangleTests::class.java)

    @Test
    @DisplayName("Chapter2. Rectangle Tests")
    fun test_rectangle() {
        val rec = Rectangle(100,100)
        log.info(rec.isSquare.toString())
        assertTrue(rec.isSquare)
    }
}
