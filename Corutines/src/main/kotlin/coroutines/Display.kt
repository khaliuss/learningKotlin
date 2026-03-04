package org.example.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.enteties.Author
import org.example.enteties.Book
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import kotlin.apply
import kotlin.concurrent.thread

object Display {

    private val infoArea = JTextArea().apply {
        isEditable = false
    }


    private val loadButton = JButton("Load Book").apply {
        addActionListener {
            GlobalScope.launch {
                isEnabled = false
                infoArea.text = "Loading book information\n"
                val book = loadBook()
                infoArea.append("Book: ${book.title}\nYear: ${book.year}\nGender: ${book.gender}\n")
                infoArea.append("Loading author information\n")

                val author = loadAuth()
                infoArea.append("Name: ${author.name}\nBio: ${author.bio}\n")
                isEnabled = true
            }
        }
    }
    private val timerLabel = JLabel("Time: 00:00")
    private val topPanel: JPanel = JPanel(BorderLayout()).apply {
        add(timerLabel, BorderLayout.WEST)
        add(loadButton, BorderLayout.EAST)
    }

    private val mainFrame: JFrame = JFrame("Book and Author info").apply {

        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(infoArea), BorderLayout.CENTER)
        size = Dimension(400, 300)
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private suspend fun loadBook(): Book {
        delay(1000)
        return Book("Bobo Jon", 1945, "Kingo")
    }

    private suspend fun loadAuth(): Author {
        delay(1000)
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