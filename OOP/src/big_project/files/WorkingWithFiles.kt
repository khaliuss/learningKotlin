package big_project.files

import java.io.File

fun main() {
    val operationCodes = OperationCode.entries
    val file = File("todo_list.txt")
    while (true) {
        print("Enter the operation code. ")
        for ((index, code) in operationCodes.withIndex()) {
            print("$index - ${code.title}")
            if (index < operationCodes.size - 1) {
                print(", ")
            } else {
                print(": ")
            }
        }
        val operationIndex = readln().toInt()
        val operationCode = operationCodes[operationIndex]
        when (operationCode) {
            OperationCode.EXIT -> break

            OperationCode.ADD_NEW_ITEM -> {
                print("Enter a new item: ")
                val item = readln()
                file.appendText("$item\n")
            }

            OperationCode.SHOW_ALL_ITEMS -> {
                val content = file.readText().trim()
                val items = content.split(  "\n")
                for ((index, item) in items.withIndex()) {
                    println("$index - $item")
                }
            }
        }
    }
}