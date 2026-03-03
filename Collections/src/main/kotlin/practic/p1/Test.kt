package org.example.practic.p1


import kotlin.random.Random

fun main() {

    //For using treeSet we have implement comparable in a class
    //which we us in this collection for ex here is: Item or add in construction comparable
    //like in class Jojo in collection treeSet2
    val treeSet = sortedSetOf<Item>()

    //functional interface with one method we can change it by lambda,
    // but we will leave it like this
    val treeSet2 =sortedSetOf<Jojo>(object : Comparator<Jojo>{
        override fun compare(o1: Jojo, o2: Jojo): Int {
            return if(o1.value > o2.value)  1
            else if(o1.value < o2.value) -1
            else 0
        }

    })

    val a = ""

    repeat(100){
        treeSet.add(Item(Random.nextInt(100)))
    }

    for (item in treeSet){
        println(item.value)
    }


}

data class Jojo (val value: Int)