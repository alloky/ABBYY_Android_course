package task_4

fun main(args: Array<String>) {
    val names = MyArrayList<String>();

    names.add("Pupa")
    names.add("Za")
    names.add("Lupa")

    names.delete(1)
    names.insert("and", 1)

    println("Hello World! Welcome ${names[0]} ${names[1]} ${names[2]} !")
}


