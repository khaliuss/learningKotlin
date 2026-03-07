package second.coroutinesFromCallbakcs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import second.enteties.Author
import second.enteties.Book
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

object
Display {

    private val scope = CoroutineScope(Dispatchers.Default)
    private val timerLabel = JLabel("Timer: 00:00")

    private val textArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load Book").apply {
        addActionListener {
            scope.launch {
                isEnabled = false
                textArea.text = "Loading book information\n"
                val book = loadBook()
                textArea.append("Book: ${book.title}\nYear: ${book.year}\nGender: ${book.gender}\n")
                val author = loadAuthor(book)
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
    }

    fun show() {
        startTimer()
        mainFrame.isVisible = true
    }


    private suspend fun loadBook(): Book {
        return suspendCoroutine<Book> { continuation ->
            loadBook { book ->
                continuation.resumeWith(Result.success(book))
            }
        }
    }

    private fun loadBook(callback: (Book) -> Unit) {
        thread {
            Thread.sleep(1000)
            callback(Book("Bobo Jon", 1945, "Kingo"))
        }
    }


    private suspend fun loadAuthor(book: Book): Author {
        return suspendCoroutine { continuation ->
            loadAuthor(book) { author ->
                continuation.resumeWith(Result.success(author))
            }
        }
    }

    private fun loadAuthor(book: Book, operation: (Author) -> Unit) {
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