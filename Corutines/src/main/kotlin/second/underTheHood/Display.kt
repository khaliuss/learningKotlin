package second.underTheHood

import second.enteties.Author
import second.enteties.Book
import java.awt.BorderLayout
import java.awt.Dimension
import java.lang.classfile.constantpool.AnnotationConstantValueEntry
import javax.swing.*
import kotlin.concurrent.thread

object
Display {


    private val timerLabel = JLabel("Timer: 00:00")

    private val textArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load Book").apply {
        addActionListener {
            loadData()
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
    }

    fun show() {
        startTimer()
        mainFrame.isVisible = true
    }

    private fun loadBook2(): Book {
        Thread.sleep(1000)
        return Book("Book2", 2002, "Pojo")
    }

    private fun loadData(step: Int = 0, data: Any? = null) {
        when (step) {
            0 -> {
                loadButton.isEnabled = false
                textArea.text = "Loading book information\n"
                loadBook { book ->
                    book as Book
                    loadData(1, book)
                }
            }

            1 -> {
                val book = data as Book
                textArea.append("Book: ${book.title}\nYear: ${book.year}\nGender: ${book.gender}\n")
                loadAuth(book) {
                    loadData(2, it)
                }
            }

            2 -> {
                val author = data as Author
                textArea.append("Author: ${author.name}\nBio: ${author.bio}\n")
                loadButton.isEnabled = true
            }
        }
    }


    private fun loadBook(operation: (book: Book) -> Unit) {
        thread {
            Thread.sleep(1000)
            operation(Book("Bobo Jon", 1945, "Kingo"))
        }
    }

    private fun loadAuth(book: Book, operation: (author: Author) -> Unit) {
        thread {
            Thread.sleep(1000)
            operation(Author("Ami Bobo", "Bobo is a good man and author"))
        }

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