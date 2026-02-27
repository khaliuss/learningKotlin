package shelter

import comand.Command


class Administrator {

    val userRepository = ShelterRepository.getInstance("qwerty")

    fun work() {
        while (true){
            print("Enter an operation: ")
            val operations = Operation.entries

            for ((index: Int, operation: Operation) in operations.withIndex()) {
                print("$index - ${operation.title}")
                if (index == operations. lastIndex) {
                    print(": ")
                } else {
                    print(", ")
                }
            }
            val input = readln().toInt()
            val operation = Operation.entries[input]
            when (operation) {
                Operation.EXIT -> {
                    userRepository.save()
                    break
                }
                Operation.ADD -> addUser()
                Operation.DELETE -> deleteUser()
            }
        }
    }

    private fun deleteUser() {
        print("Enter id: ")
        val id = readln().toInt()
        //I left it like this bc sometimes I forget how it works
        DogsInvoker.addCommand(object: Command{
            override fun execute() {
                userRepository.deleteUser(id)
            }
        })

    }

    private fun addUser() {
        print("Enter firstname: ")
        val firstName = readln()
        print("Enter lastname: ")
        val lastName = readln()
        print("Enter weight: ")
        val weight = readln().toDouble()
        DogsInvoker.addCommand {
            userRepository.addUser(firstName,lastName,weight)
        }
    }

}