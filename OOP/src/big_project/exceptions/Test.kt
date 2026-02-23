package big_project.exceptions

import big_project.corporation.workers.WorkerRepository

fun main() {
    val director = WorkerRepository.findDirector() ?: throwDirectorIsRequired()
    director.printInfo()
}

fun throwDirectorIsRequired(): Nothing {
    throw IllegalStateException("Director is required for this program. Please add it to the file workers.txt")
}