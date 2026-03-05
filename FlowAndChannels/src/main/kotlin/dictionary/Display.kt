package org.example.dictionary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

object Display {

    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val repository = Repository
    var loadingJob: Job? = null

    val enterWorldLabel = JLabel("Enter word: ")
    val searchField = JTextField(20).apply {
        addKeyListener(object : KeyAdapter() {
            override fun keyReleased(e: KeyEvent?) {
                loadingDefinitions()
            }
        })
    }
    val searchButton = JButton("Search").apply {
        addActionListener {
            loadingDefinitions()
        }
    }
    val resultArea = JTextArea(15, 50).apply {
        isEditable = false
        lineWrap = true
        wrapStyleWord = true

    }

    val topPanel = JPanel().apply {
        add(enterWorldLabel)
        add(searchField)
        add(searchButton)
    }

    val mainFrame = JFrame("Dictionary").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(resultArea), BorderLayout.CENTER)
        pack()
    }

    private fun loadingDefinitions() {
        loadingJob?.cancel()
        loadingJob = scope.launch {
            searchButton.isEnabled = false
            resultArea.text = "Searching the word...."
            delay(500)
            val word = searchField.text.trim()
            val definition = repository.loadingDefinition(word).joinToString("\n\n")
            resultArea.text = definition.ifEmpty { "Not Found" }
            searchButton.isEnabled = true
        }
    }

    fun show() {
        mainFrame.isVisible = true
    }

}


fun main() {
    Display.show()
}