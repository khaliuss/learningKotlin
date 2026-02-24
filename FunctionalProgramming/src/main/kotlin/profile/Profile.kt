package org.example.profile

fun main() {


    val persons = ProfilesRepository.profiles

    var filtered = filter(persons) { it.age > 25 }
    filtered = filter(filtered) { it.firstName.startsWith("A") }
    filtered = filter(filtered) { it.gender == Gender.MALE }

//    val names = transform(filtered){it.firstName}
//    val lastNames = transform(filtered){it.lastName}
//    val fullNames = transform(filtered){"${it.firstName} ${it.lastName}"}
    val transformed = transform(filtered){it.copy(age = it.age+1)}

    for (person in transformed) {
        println(person)
    }

}

fun <T> transform(profiles: List<Person>,operation:(Person) -> T): List<T>{
    val result = mutableListOf<T>()
    for (person in profiles){
        result.add(operation(person))
    }
    return result
}

fun filter(persons: List<Person>, isSuitable: (Person) -> Boolean): List<Person> {
    val newPersons = mutableListOf<Person>()
    for (person in persons) {
        if (isSuitable(person)) {
            newPersons.add(person)
        }
    }
    return newPersons
}


