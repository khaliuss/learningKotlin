package users


class Administrator {

    val userRepository = UserRepository.getInstance("qwerty")

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
        UserInvoker.addCommand {
            userRepository.deleteUser(id)
        }
    }

    private fun addUser() {
        print("Enter firstname: ")
        val firstName = readln()
        print("Enter lastname: ")
        val lastName = readln()
        print("Enter age: ")
        val age = readln().toInt()

        UserInvoker.addCommand {
            userRepository.addUser(firstName,lastName,age)
        }
    }

}