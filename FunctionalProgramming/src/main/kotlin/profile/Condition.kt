package org.example.profile

interface Condition {
    fun isSuitable(person: Person): Boolean
}