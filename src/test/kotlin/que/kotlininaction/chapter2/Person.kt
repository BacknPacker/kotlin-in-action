package que.kotlininaction.chapter2

class Person(
    val name:String,
    var isMarried:Boolean
)

fun main() {
    println("Hell World")
    println(max(1,2))
    println(max2(1,2))
    println(question)
    println(answer)
    println(answer2)

    val languages = arrayListOf("Java")
    languages.add("Kotlin")

    val person = Person("Que", false)
    println(person.isMarried)
    person.isMarried = true
    println(person.isMarried)
}

fun max(a:Int, b:Int): Int {
    return if(a>b) a else b
}

fun max2(a:Int, b:Int): Int = if(a>b) a else b

val question = "Hell World"
val answer = 42
val answer2:Int = 42

