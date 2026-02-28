package users

import comand.Command

interface AdministratorCommands: Command

data class AddUser(
    val repository: UserRepository,
    val firstName: String,
    val lastName: String,
    val age: Int
): AdministratorCommands{
    override fun execute() {
        repository.addUser(firstName,lastName,age)
    }

}

data class DeleteUser(
    val repository: UserRepository,
    val id: Int
): AdministratorCommands{
    override fun execute() {
        repository.deleteUser(id)
    }

}


data class SaveChanges(
    val repository: UserRepository,
): AdministratorCommands{
    override fun execute() {
        repository.save()
    }
}