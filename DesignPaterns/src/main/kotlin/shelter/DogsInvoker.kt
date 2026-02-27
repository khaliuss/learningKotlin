package shelter

import comand.Command
import comand.Invoker
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

object DogsInvoker : Invoker {

    private val commands = LinkedBlockingQueue<Command>()

    init{
        thread {
            while (true){
                val command = commands.take()
                command.execute()
            }
        }
    }

    override fun addCommand(command: Command) {
        commands.add(command)
    }
}