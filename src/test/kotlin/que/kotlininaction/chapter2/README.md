# Chapter 2. 코틀린 기초
- - -
* 2장에서 다루는 내용
  * 함수, 변수, 클래스, enum, 프로퍼티를 선언하는 방법
  * 제어 구조
  * 스마트 캐스트
  * 예외 던지기와 예외 잡기

- - -

## 기본 요소: 함수와 변수

```kotlin
fun main() {
  println("Hell world")
}
```

<br>

* 함수를 선언할 때 fun키워드를 사용한다.
* 파라미터 이름 뒤에 그 파라미터의 타입을 쓴다.
* 함수를 최상위 수준에 정의할 수 있다.
* 자바와 달리 배열 처리를 위한 문법이 따로 존재하지 않는다.
* 세미콜론은 붙이지 않아도 된다.

<br>

## 함수

```kotlin
fun max(a:Int, b:Int): Int {
    return if(a>b) a else b
}
```

<br>

> if는 문장이 아니고 결과를 만드는 식이다.

<br>



## 식이 본문인 함수

```kotlin
fun max2(a:Int, b:Int): Int = if(a>b) a else b
```
<br>
* 본문이 중괄호로 둘러싸인 함수를 블록이 본문인 함수라 부르고, 등호화 식으로 이뤄진 함수를 식이 본문인 함수라고 부른다. <br>
* 컴파일러가 타입을 분석해 프로그래머 대신 프로그램 구성 요소의 타입을 정해준다. (타입 추론) <br>

<br>

## 변수
```kotlin
// 코틀린에서는 키워드로 변수 선언을 시작하는 대신
// 변수 이름 뒤에 타입을 명시하거나 생략하게 허용한다.
val question = "Hell World"
val answer = 42
val answer2:Int = 42
```

<br>

## 변경 가능한 변수와 변경 불가능한 변수

* `val` : 변경 불가능한 참조를 저장하는 변수
* `var` : 변경 가능한 참조다.
* 기본적으로는 모든 변수를 `val` 키워드를 사용해 불변 변수로 선언하고, 나중에 꼭 필요할 때에만 `var`로 변경하라.

<br>

```kotlin
val languages = arrayListOf("Java")
languages.add("Kotlin")
```

* `val` 참조 자체는 불변일지라도 그 참조가 가리키는 객체의 내부 값은 변경될 수 있다.

<br>

## 더 쉽게 문자열 형식 지정: 문자열 템플릿

```kotlin
fun main(args: Array<String>) {
  val name = if (args.isNotEmpty()) args[0] else "Kotlin"
  println("Hello, $name!")
}
```
```kotlin
fun main(args: Array<String>){
    if(args.size > 0){
      println("Hello, ${args[0]}!")
    }
}
```

```kotlin
fun main(args: Array<String>) {
  println("Hello, ${if (args.isNotEmpty()) args[0] else "someone"}!")
}
```


<br>

## 클래스

```kotlin
class Person(val name:String)
```
<br>

* 이런 유형의 클래스를 값 객체라 부르며, 다양한 언어가 값 객체를 간결하게 기술할 수 있는 구문을 제공한다.
* 코틀린의 기본 가시성은 `publice`이므로 이런 경우 변경자를 생략해도 된다.

<br>

## 프로퍼티

코틀린은 프로퍼티를 언어 기본 기능으로 제공하며, 코틀린 프로퍼티는 자바의 필드와 접근자 메서드를 완전히 대신한다. <br>

```kotlin
class Person(
  val name:String, // 읽기 전용 프로퍼티
  var isMarried:Boolean // 쓸 수 있는 프로퍼티
)
```

<br>

대부분의 프로퍼티에는 그 프로퍼티의 값을 저장하기 위한 필드가 있다. 이를 프로퍼티를 뒷받침하는 필드라고 부른다. 하지만 원한다면 프로퍼티 값을 그때그때 계산할 수도 있다. <br>

<br>

## 커스텀 접근

```kotlin
class Rectangle(val height:Int, val witdh:Int) {
    val isSquare : Boolean
        get(){          // 프로퍼티 게터 선언
            return height == witdh
        }
}
```
<br>

`isSquare` 프로퍼티에는 자체 값을 저장하는 필드가 필요없다. 이 프로퍼티에는 자체 구현을 제공하는 게터만 존재한다. <br>

<br>

## enum

코틀린에서 enum은 소프트 키워드라 부르는 존재다. enum은 class 앞에 있을 때는 특별한 의미를 지니지만 다른 곳에서는 이름에 사용할 수 있다.
반면 class는 키워드다. 따라서 class라는 이름을 사용할 수 없으므로 클래스를 표현하는 변수 등을 정의할 때는 clazz나 aClass와 같은 이름을 사용해야 한다. <br>
자바와 마찬가지로 enum은 단순히 값만 열거하는 존재가 아니다. enum 클래스 안에도 프로퍼티나 메서드를 정의할 수 있다. <br>

```kotlin
enum class Color(
    val r: Int, val g: Int, val b: Int // 상수 프로퍼티 정의
) {
    RED(255,0,0), ORANGE(255,165,0),
    YELLOW(255,255,0), GREEN(0, 255, 0),
    BLUE(0,0,255), INDIGO(75,0,130),
    VIOLET(238,130,238); // 마지막에 반드시 세미콜론

    fun rgb() = (r*256+g)*256+b // enum 클래스 내에 메서드 정의
}
```

<br>

## when으로 enum 클래스 다루기

* when을 사용해 올바른 enum 찾기 <br>

```kotlin
fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }
```

<br>

* when의 분기 조건에 여러 다른 객체 사용하기 <br>

```kotlin
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        else -> throw Exception("Dirty color")
    }
```

<br>

* 인자가 없는 when <br>

```kotlin
fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
        else -> throw Exception("Dirty color")
    }
```

<br>

## 스마트 캐스트

* 식으로 표현하는 클래스 계층 <br>

```kotlin
interface Expr
class Num (val value: Int) : Expr
class Sum (val left: Expr, val right: Expr) : Expr
```

<br>

* if 연쇄를 사용해 식을 계산하기(자바 스타일) <br>

```kotlin
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
```

<br>

* 리팩토링: 값을 만들어내는 if식 <br>

```kotlin
fun eval(e: Expr): Int =
  if(e is Num){
    e.value
  } else if (e is Sum){
    eval(e.right) + eval(e.left)
  } else {
    throw IllegalArgumentException("Unknown expression")
  }
```
<br>

* 리팩토링: if 중첩 대신 When 사용하기 <br>

```kotlin
fun eval(e: Expr): Int =
  when(e){
    is Num -> e.value
    is Sum -> eval(e.right) + eval(e.left)
    else -> throw IllegalArgumentException("Unknown expression")
  }
```

<br>

* if와 when의 분기에서 블록 사용 <br>

```kotlin
fun evalWithLogging(e: Expr) : Int =
    when(e){
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
            
    }
```

<br>

## 대상을 이터레이션: while과 for 루프

* when을 사용해 피즈버즈 게임 구현하기 <br>

```kotlin
fun fizzBuzz(i: Int) =
    when {
        i%15 == 0 -> "FizzBuzz"
        i%3 == 0 -> "Fizz"
        i%5 == 0 -> "Buzz"
        else -> "$i "
    }

fun main() {
  for (i in 1..100){ // 1..100 범위의 정수에 대해
    print(fizzBuzz(i))
  }
  println()
  for(i in 100 downTo 1 step 2){ // 100 downTo 1로 역방
    print(fizzBuzz(i))
  }
}
```

<br>

* 맵에 대한 이터레이션 <br>

```kotlin
val binaryReps = TreeMap<Char, String>()

for (c in 'A'..'F'){
    val binary = Integer.toBinaryString(c.code)
    binaryReps[c] = binary
}

for ((letter, binary) in binaryReps) {
    println("$letter = $binary")
}
```

<br>

* in으로 컬렉션이나 범위의 원소 검사 <br>

```kotlin
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
```

<br>

## try, catch, finally

* 자바와 마찬가지로 try 사용하기 <br>

```kotlin
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

val reader = BufferedReader(StringReader("eee"))
println(readNumber(reader))
```

<br>

* try를 식으로 사용하기 <br>

```kotlin
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
```

<br>

- - -

## 요약

* 함수를 정의할 때 fun 키워드를 사용한다. <br>
* 문자열 템플릿을 사용하면 문자열을 연결하지 않아도 되므로 코드가 간결해진다. <br>
* 코틀린에서는 값 객체 클래스를 아주 간결하게 표현할 수 있다. <br>
* 다른 언어에도 있는 if는 코틀린에서 식이며, 값을 만들어낸다. <br>
* 코틀린 when은 자바의 switch와 비슷하지만 더 강력하다. <br>
* 어떤 변수의 타입을 검사하고 나면 굳이 그 변수를 캐스팅하지 않아도 검사한 타입의 변수처럼 사용할 수 있다. <br>
* for, while, do-while 루프는 자바가 제공하는 같은 키워드의 기능과 비슷하다. <br>
* 1..5와 같은 식은 범위를 만들어낸다. <br>
* 코틀린 예외 처리는 자바와 비슷하다. <br>