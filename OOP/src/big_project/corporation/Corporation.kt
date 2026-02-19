package big_project.corporation

import big_project.corporation.shop.FoodCard
import big_project.corporation.workers.Accountant
import big_project.corporation.workers.Assistant
import big_project.corporation.workers.Cleaner
import big_project.corporation.workers.Consultant
import big_project.corporation.workers.Director
import big_project.corporation.workers.Supplier
import big_project.corporation.workers.Worker
import big_project.corporation.workers.WorkerRepository

fun main() {

    val workers = WorkerRepository.workers;

    for (worker in workers){
        worker.work()
    }




}