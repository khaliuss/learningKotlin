package org.example.callbacks

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

object Display {

    private val infoArea = JTextArea().apply {
        isEditable = false
    }


    private val loadButton = JButton("Load Book").apply {
        addActionListener {
            isEnabled = false
            infoArea.text = "Loading book information\n"
            val book = loadBook()
            infoArea.append("Book: ${book.title}\nYear: ${book.year}\nGender: ${book.gender}\n")
            infoArea.append("Loading author information\n")
            val auth = loadAuth(book)
            infoArea.append("Name: ${auth.name}\nBio: ${auth.bio}\n")
            isEnabled = true
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

    private fun loadBook(): Book {
        Thread.sleep(1000)
        return Book("Bobo Jon", 1945, "Kingo")
    }

    private fun loadAuth(book: Book): Author {
        Thread.sleep(1000)
        return Author("Ami Bobo", "Bobo is a good man and author")

    }


    private fun startTimer() {
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