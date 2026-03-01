package org.example.practic.p1

fun main() {
    val arr = PracticeHashSet()
    arr.add(4)
    arr.add(20)
    arr.add(36)


    arr.arrayList.forEach ( ::println )

    arr.remove(36)
    arr.remove(20)

    arr.arrayList.forEach ( ::println )

    arr.remove(4)
    arr.arrayList.forEach ( ::println )

}