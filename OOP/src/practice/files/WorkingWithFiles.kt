package practice.files

import practice.enums.ProductType
import java.io.File

val file = File("taskList.txt")

fun main() {
    choosingOperation()
    writeTask()
    readTasks()
}

private fun choosingOperation(){
    val operations = OperationCode.entries
    while (true){
        print("Enter the operation code. ")
        for ((index,operation) in operations.withIndex()){
            print("$index - ${operation.title}")
            if (index < operations.size-1){
                print(", ")
            }else{
                print(": ")
            }
        }
        val codeIndex = readln().toInt()
        val operation = operations[codeIndex]

        when (operation){
            OperationCode.EXIT -> break
            OperationCode.ADD_NEW_TASK -> writeTask()
            OperationCode.SHOW_ALL_TASKS -> readTasks()
        }



    }

}

private fun writeTask(){
    while (true){
        val task = readln()
        file.appendText("$task\n")
    }
}

private fun readTasks(){
    val content = file.readText().trim()
    val items = content.split("\n")
    val itemList  = mutableListOf<String>()
    for ((index,item) in items.withIndex()){
        println("$index - $item")
    }



}