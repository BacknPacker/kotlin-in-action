package que.kotlininaction.chapter2

class Rectangle(val height:Int, val witdh:Int) {
    val isSquare : Boolean
        get(){
            return height == witdh
        }
}

fun main() {
    val rec = Rectangle(100,100)
    println(rec.isSquare)
}