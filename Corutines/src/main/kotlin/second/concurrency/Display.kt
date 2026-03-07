package second.concurrency

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import second.enteties.Book
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.concurrent.Executors
import javax.swing.*
import kotlin.concurrent.thread

object
Display {


    private val timerLabel = JLabel("Timer: 00:00")
    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val scope = CoroutineScope(CoroutineName("MyCoroutine") + dispatcher)

    private val textArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load Book").apply {
        addActionListener {
            isEnabled = false
            textArea.text = "Loading book information\n"

            val jobs = mutableListOf<Deferred<Book>>()
            repeat(10) {
                scope.async {
                    val book = loadBook()
                    textArea.append("Book $it: ${book.title}\nYear: ${book.year}\nGender: ${book.gender}\n\n")
                    book
                }.also {job->
                    jobs.add(job)
                }
            }
            scope.launch {
                val books = jobs.awaitAll()
                println(books.joinToString(", "))
                isEnabled = true
            }
        }
    }


    val panel = JPanel().apply {
        layout = BorderLayout()
        add(timerLabel, BorderLayout.WEST)
        add(loadButton, BorderLayout.EAST)
    }

    val mainFrame = JFrame("Book and Author").apply {
        layout = BorderLayout()
        add(panel, BorderLayout.NORTH)
        add(JScrollPane(textArea), BorderLayout.CENTER)
        size = Dimension(600, 400)

        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                scope.cancel()

            }
        })
    }

    fun show() {
        startTimer()
        mainFrame.isVisible = true
    }


    private suspend fun loadBook(): Book {
        return withContext(Dispatchers.Default) {
            delay(3000)
            Book("Bobo Jon", 1945, "Kingo")
        }
    }


    private fun startTimer() {
        scope.launch {
            var totalSeconds = 0
            while (true) {
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerLabel.text = String.format("Timer: %02d:%02d", minutes, seconds)
                delay(1000)
                totalSeconds++
            }
        }
    }

}