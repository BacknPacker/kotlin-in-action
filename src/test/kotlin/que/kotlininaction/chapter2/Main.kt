package que.kotlininaction.chapter2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import java.io.BufferedReader
import java.io.StringReader
import java.util.*

@SpringBootTest
class Main {
    
    val log = LoggerFactory.getLogger(Main::class.java)

    @Test
    @DisplayName("Chapter2. Main Test")
    fun test_main() {
        fun fizzBuzz(i: Int) =
            when {
                i%15 == 0 -> "FizzBuzz"
                i%3 == 0 -> "Fizz"
                i%5 == 0 -> "Buzz"
                else -> "$i "
            }

        for (i in 1..100){
            log.info(fizzBuzz(i))
        }


        for(i in 100 downTo 1 step 2){
            log.info(fizzBuzz(i))
        }


        val binaryReps = TreeMap<Char, String>()

        for (c in 'A'..'F'){
            val binary = Integer.toBinaryString(c.code)
            binaryReps[c] = binary
        }

        for ((letter, binary) in binaryReps) {
            log.info("$letter = $binary")
        }

        fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
        fun isNotDigit(c: Char) = c !in '0'..'9'

        Assertions.assertTrue(isLetter('q'))
        Assertions.assertTrue(isNotDigit('x'))
        /*
            fun readNumber(reader: BufferedReader): Int? {
                try{
                    val line = reader.readLine()
                    return Integer.parseInt(line)
                } catch (e: NumberFormatException) {
                    return null
                } finally {
                    reader.close()
                }
            }
        */
        fun readNumber(reader: BufferedReader): Int? {
            val number =
                try{
                    Integer.parseInt(reader.readLine())
                } catch (e: NumberFormatException) {
                    null
                } finally {
                    reader.close()
                }
            return number
        }

        val reader = BufferedReader(StringReader("eee"))
        assertNull(readNumber(reader))
    }
}




