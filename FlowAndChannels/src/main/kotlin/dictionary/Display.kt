package org.example.dictionary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

object Display {

    private lateinit var queries: Flow<String>
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val repository = Repository

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

    }

    fun show() {
        mainFrame.isVisible = true
    }

    init {

        //method 1
        /*scope.launch {
            queries
                .onEach {
                    searchButton.isEnabled = false
                    resultArea.text = "Searching the word...."
                }.map {
                    repository.loadingDefinition(it)
                }.map {
                    it.joinToString("\n\n").ifEmpty { "Not Found" }
                }.onEach {
                    resultArea.text = it
                    searchButton.isEnabled = true
                }.collect()
        }*/


        //method 2
        queries
            .onEach {
                searchButton.isEnabled = false
                resultArea.text = "Searching the word...."
            }.map {
                repository.loadingDefinition(it)
            }.map {
                it.joinToString("\n\n").ifEmpty { "Not Found" }
            }.onEach {
                resultArea.text = it
                searchButton.isEnabled = true
            }.launchIn(scope)

    }

}


fun main() {
    Display.show()
}