package que.kotlininaction.chapter3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FunctionTests {

    val log = LoggerFactory.getLogger(FunctionTests::class.java)

    @Test
    @DisplayName("Chapter3 Collection Tests")
    fun test_collections(){
        val set = hashSetOf(1,7,53, 3) // 순서 x
        log.info("set = $set")

        assertEquals(4, set.size)

        val list = arrayListOf(1, 7, 53, 3) // 순서 o
        log.info("list = $list")

        val search = list.binarySearch(53) // ArrayList는 순서가 있으므로 이분탐색 가능
        log.info("search = $search")

        val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
        log.info("map = $map")

        log.info("map.javaClass = ${map.javaClass}")

        assertEquals("class java.util.HashMap", map.javaClass.toString())

        val listOf = listOf(1, 2, 3)
        // log.info("joinToString = ${joinToString(listOf,",","(",")")}")
        // log.info("joinToString = ${joinToString(listOf, separator = ",", prefix = "(", postfix = ")")}")
        // log.info("joinToString = ${joinToString(listOf)}")
        log.info("listOf = ${listOf.joinToString()}")
    }

    fun <T> Collection<T>.joinToString(
        collection: Collection<T>,
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
    ) : String{
        val result = StringBuilder(prefix)
        for ((index, element) in this.withIndex()) {
            if(index > 0) result.append(separator)
            result.append(element)
        }
        result.append(postfix)
        return result.toString()
    }

    open class View {
        open fun click() = println("View clicked")
    }

    class Button:View() {
        override fun click() = println("Button clicked")
    }

    fun View.showOff() = println("I'm a view")
    fun Button.showOff() = println("I'm a button")

    val String.lastChar: Char
        get() = get(length - 1)

    var StringBuffer.lastChar: Char
        get() = get(length - 1)
        set(value: Char) {
            this.setCharAt(length - 1, value)
        }

    @Test
    @DisplayName("Chapter3 Extend Tests")
    fun test_extend(){
        val view:View = Button()
        view.click()
        view.showOff()

        log.info("Kotlin".lastChar.toString())

        val sb = StringBuffer("Kotlin?")
        sb.lastChar = '!'
        log.info(sb.lastChar.toString())
    }


}