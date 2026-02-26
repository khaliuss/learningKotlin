package shelter

fun main() {

    ShelterRepository.getInstance("qwerty").dogs.forEach { println(it) }

}