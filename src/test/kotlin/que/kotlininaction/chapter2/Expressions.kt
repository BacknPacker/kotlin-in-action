package que.kotlininaction.chapter2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Expr {
    interface Expressions
    class Num (val value: Int) : Expressions
    class Sum (val left: Expressions, val right: Expressions) : Expressions
    val log = LoggerFactory.getLogger(Expr::class.java)
    @Test
    @DisplayName("Chapter2. Expressions Test")
    fun test_expr() {
        log.info(eval(Sum(Sum(Num(1),Num(2)),Num(4))).toString())
        assertEquals(7, eval(Sum(Sum(Num(1),Num(2)),Num(4))))
    }

    /* 자바 스타일 if 연쇄
    fun eval(e: Expr): Int {
        if(e is Num){
            val n = e as Num
            return n.value
        }
        if (e is Sum){
            return eval(e.right) + eval(e.left)
        }
        throw IllegalArgumentException("Unknown expression")
    }
    */

    /* 값을 만들어내는 if식
    fun eval(e: Expr): Int =
        if(e is Num){
            e.value
        } else if (e is Sum){
             eval(e.right) + eval(e.left)
        } else {
            throw IllegalArgumentException("Unknown expression")
        }
    */


    fun eval(e: Expressions): Int =
        when(e){
            is Num -> e.value
            is Sum -> eval(e.right) + eval(e.left)
            else -> throw IllegalArgumentException("Unknown expression")
        }

    fun evalWithLogging(e: Expressions) : Int =
        when(e){
            is Num -> {
                log.info("num: ${e.value}")
                e.value
            }
            is Sum -> {
                val left = evalWithLogging(e.left)
                val right = evalWithLogging(e.right)
                log.info("sum: $left + $right")
                left + right
            }
            else -> throw IllegalArgumentException("Unknown expression")

        }
}


