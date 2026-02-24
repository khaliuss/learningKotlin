package org.example.profile

class ConditionOlderThen25 : Condition{

    override fun isSuitable(person: Person): Boolean {
        return person.age > 25
    }

}