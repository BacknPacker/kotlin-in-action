package que.kotlininaction.chapter2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Member {
    class Person(
        val name:String,
        var isMarried:Boolean
    )

    private val log = LoggerFactory.getLogger(Member::class.java)
    private val question = "Hell World"

    @Test
    @DisplayName("Chapter2. Person Test")
    fun test_member() {
        val languages = arrayListOf("Java")
        languages.add("Kotlin")

        val person = Person("Que", false)
        log.info(person.isMarried.toString())
        person.isMarried = true
        log.info(person.isMarried.toString())

        assertEquals("Hell World", question)
        assertEquals(2, max(1,2))
        assertEquals(2, maxExpression(1,2))
    }
    fun max(a:Int, b:Int): Int {
        return if(a>b) a else b
    }

    fun maxExpression(a:Int, b:Int): Int = if(a>b) a else b
}



