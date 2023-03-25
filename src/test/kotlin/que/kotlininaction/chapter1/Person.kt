package que.kotlininaction.chapter1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class Person {

    val log = LoggerFactory.getLogger(Person::class.java)

    data class Member(val name: String,     // '데이터' 클래스
                      val age: Int? = null) // 널이 될 수 있는 타입(Int?)과 파라미터 디폴트 값

    @Test
    @DisplayName("Chapter1. Person Test")
    fun test_person() {     // 최상위 함수
        val members = listOf(Member("영희"),
                                Member("철수", age = 29)) // 이름 붙인 파라미터
        val oldest = members.maxByOrNull { it.age?:0 }     // 람다 식과 엘비스 연산자
        log.info("나이가 가장 많은 사람: $oldest")

        assertEquals(2, members.size)
        assertEquals(29, oldest?.age)
        assertEquals("철수", oldest?.name)
    }
}

