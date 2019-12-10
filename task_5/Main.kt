package task_5

class Test {
    var value: Int by StorageDelegate("value", 0)
    var x: Double by StorageDelegate("x", 3.5)
}

fun main(){
    val t = Test()
    t.value = 10
    t.x = 14.5
    print("${t.value} ${t.x}")
}