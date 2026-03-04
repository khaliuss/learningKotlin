package org.example.concurrency

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.enteties.Author
import org.example.enteties.Book
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import java.util.concurrent.Executors
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import kotlin.apply
import kotlin.concurrent.thread

object Display {

    val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private val scope = CoroutineScope(CoroutineName("My coroutine") + dispatcher)

    private val infoArea = JTextArea().apply {
        isEditable = false
    }


    private val loadButton = JButton("Load Book").apply {
        addActionListener {

            isEnabled = false
            infoArea.text = "Loading book information\n"
            val jobs = mutableListOf<Job>()
            repeat(10) {
                scope.launch {
                    val book = loadBook()
                    infoArea.append("Book: $it ${book.title}\nYear: ${book.year}\nGender: ${book.gender}\n\n")
                }.also { jobs.add(it) }
            }

            scope.launch {
                jobs.joinAll()
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
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                scope.cancel()
            }
        })
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private fun longOperation() {
        mutableListOf<Int>().apply {
            repeat(300_000) {
                add(0, it)
            }
        }
    }

    private suspend fun loadBook(): Book {
        return withContext(Dispatchers.Default) {
            longOperation()
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