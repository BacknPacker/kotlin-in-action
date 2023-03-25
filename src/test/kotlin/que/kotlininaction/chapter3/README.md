# Chapter 3. 함수 정의와 호출
- - -
* 3장에서 다루는 내용
    * 컬렉션, 문자열, 정규식을 다루기 위한 함수
    * 이름 붙인 인자, 디폴트 파라미터 값, 중위 호출 문법 사용
    * 확장 함수와 확장 프로퍼티를 사용해 자바 라이브러리 적용
    * 최상위 및 로컬 함수와 프로퍼티를 사용해 코드 구조화

- - -

## 컬렉션 만들기

```kotlin
val set = hashSetOf(1,7,53, 3) // 순서 x
log.info("set = $set")

val list = arrayListOf(1, 7, 53, 3) // 순서 o
log.info("list = $list")

val search = list.binarySearch(53) // ArrayList는 순서가 있으므로 이분탐색 가능
log.info("search = $search")

val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
log.info("map = $map")

log.info("{map.javaClass} = ${map.javaClass}")
```

코틀린은 자신만의 컬렉션 기능을 제공하지 않는다. 기존 자바 컬렉션을 활용한다. 따라서, 표준 자바 컬렉션을 활용하면 자바 코드와 상호작용하기가 훨씬 더 쉽다.
자바에서 코틀린 함수를 호출하거나 코틀린에서 자바 함수를 호출할 때 자바와 코틀린 컬렉션을 서로 변환할 필요가 없다. <br>
<br>

## 함수를 호출하기 쉽게 만들기

* joinToString() 함수의 초기 구현 <br>

```kotlin
fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
) : String{
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if(index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
```
<br>
<span style="font-size: 20px">✅ <strong>이름 붙인 인자</strong></span> <br>

```kotlin
joinToString(listOf, separator = ",", prefix = "(", postfix = ")")
```
<br>

<span style="font-size: 20px">✅ <strong>디폴트 파라미터 값</strong></span> <br>

```kotlin
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) : String{
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if(index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

log.info("joinToString = ${joinToString(listOf)}") // joinToString = 1, 2, 3
```
<br>

<span style="font-size: 20px">✅ <strong>확장 함수로 유틸리티 함수 정의</strong></span> <br>

```kotlin
fun <T> Collection<T>.joinToString( // Collection<T>에 대한 확장 함수를 선언
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) : String{
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) { // "this"는 수신 객체
        if(index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

log.info("listOf = ${listOf.joinToString()}") // listOf = 1, 2, 3
```
<br>

<span style="font-size: 20px">✅ <strong>확장 함수는 오버라이드할 수 없다</strong></span> <br>

```kotlin
open class View {
        open fun click() = println("View clicked")
    }

    class Button:View() {
        override fun click() = println("Button clicked")
    }

    fun View.showOff() = println("I'm a view")
    fun Button.showOff() = println("I'm a button")

    @Test
    @DisplayName("Chapter3 Extend Tests")
    fun test_extend(){
        val view:View = Button()
        view.click()    // Button clicked
        view.showOff()  // I'm a view
    }
```
<br>

<span style="font-size: 20px">✅ <strong>확장 프로퍼티</strong></span> <br>

```kotlin
val String.lastChar: Char
    get() = get(length - 1)

log.info("Kotlin".lastChar.toString()) // n

var StringBuffer.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

val sb = StringBuffer("Kotlin?")
sb.lastChar = '!'
log.info(sb.lastChar.toString()) // !
```
<br>

- - -

## 요약

* 코틀린은 자체 컬렉션 클래스를 정의하지 않지만 자바 클래스를 확장해서 더 풍부한 API를 제공한다.
* 함수 파라미터의 디폴트 값을 정의하면 오버로딩한 함수를 정의할 필요성이 줄어든다
* 이름 붙인 인자를 사용하면 함수의 인자가 많을 때 함수 호출의 가독성을 높인다.
* 중위 호출을 통해 인자가 하나 밖에 없는 메서드나 확장 함수를 더 깔끔한 구문으로 호출할 수 있다.