package que.kotlininaction.chapter2

import java.io.BufferedReader
import java.io.StringReader
import java.util.*

fun main() {
    fun fizzBuzz(i: Int) =
        when {
            i%15 == 0 -> "FizzBuzz"
            i%3 == 0 -> "Fizz"
            i%5 == 0 -> "Buzz"
            else -> "$i "
        }

    for (i in 1..100){
        print(fizzBuzz(i))
    }

    println()

    for(i in 100 downTo 1 step 2){
        print(fizzBuzz(i))
    }

    println()

    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
    fun isNotDigit(c: Char) = c !in '0'..'9'

    println(isLetter('q'))
    println(isNotDigit('x'))
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
    fun readNumber(reader: BufferedReader){
        val number = try{
            Integer.parseInt(reader.readLine())
        } catch (e: NumberFormatException) {
            null
        } finally {
            reader.close()
        }
        println(number)
    }

    val reader = BufferedReader(StringReader("eee"))
    readNumber(reader)
}




