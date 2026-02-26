package users

fun main() {
    UserRepository.getInstance("qwery").users.forEach { println(it) }
}