package second.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import second.enteties.Author
import second.enteties.Book
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*
import kotlin.concurrent.thread

object
Display {


    private val timerLabel = JLabel("Timer: 00:00")
    private val scope = CoroutineScope(CoroutineName("MyCoroutine") + Dispatchers.Unconfined)

    private val textArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load Book").apply {
        addActionListener {
            scope.launch {
                isEnabled = false
                textArea.text = "Loading book information\n"
                val book = loadBook()
                println("Book loaded...")
                textArea.append("Book: ${book.title}\nYear: ${book.year}\nGender: ${book.gender}\n")
                val author = loadAuth(book)
                println("Author loaded...")
                textArea.append("Author: ${author.name}\nBio: ${author.bio}\n")
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
        add(textArea, BorderLayout.CENTER)
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

    private fun longOperation() {
        mutableListOf<Int>().apply {
            repeat(300_000) {
                add(0, it)
            }
        }
    }

    private suspend fun loadBook(): Book {
        return withContext(Dispatchers.Default){
            longOperation()
            Book("Bobo Jon", 1945, "Kingo")
        }
    }

    private suspend fun loadAuth(book: Book): Author {
        withContext(Dispatchers.Default){
            longOperation()
        }
        return Author("Ami Bobo", "Bobo is a good man and author")
    }


    private fun startTimer() {
        thread {
            var totalSeconds = 0
            while (true) {
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerLabel.text = String.format("Timer: %02d:%02d", minutes, seconds)
                Thread.sleep(1000)
                totalSeconds++
            }
        }
    }

}