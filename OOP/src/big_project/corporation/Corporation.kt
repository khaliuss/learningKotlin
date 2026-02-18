package big_project.corporation

import big_project.corporation.shop.FoodCard
import big_project.corporation.workers.Accountant
import big_project.corporation.workers.Assistant
import big_project.corporation.workers.Cleaner
import big_project.corporation.workers.Consultant
import big_project.corporation.workers.Director
import big_project.corporation.workers.Supplier
import big_project.corporation.workers.Worker

fun main() {

    val accountant = Accountant(  0, "Christian", 30)
    accountant.work()

    val foodCard = FoodCard("Chips","Pojo",20,123)
    println(foodCard.toString())

}