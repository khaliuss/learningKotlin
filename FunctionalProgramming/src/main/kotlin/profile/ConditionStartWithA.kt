package org.example.profile

class ConditionStartWithA : Condition {
    override fun isSuitable(person: Person): Boolean {
        return person.firstName.startsWith("A")
    }
}