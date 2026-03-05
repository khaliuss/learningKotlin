package org.example.dictionary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.awt.BorderLayout
import javax.swing.*

object Display {

    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val repository = Repository

    val enterWorldLabel = JLabel("Enter word: ")
    val searchField = JTextField(20)
    val searchButton = JButton("Search").apply {
        addActionListener {
            val word = searchField.text.trim()
            scope.launch {
                isEnabled = false
                resultArea.text = "Searching the word...."
                val definition = repository.loadingDefinition(word).joinToString("\n\n")
                resultArea.text = definition.ifEmpty { "Not Found" }
                isEnabled = true
            }
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

    fun show() {
        mainFrame.isVisible = true
    }

}


fun main() {
    Display.show()
}