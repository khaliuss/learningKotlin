package org.example.dictionary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

@OptIn(FlowPreview::class)
object Display {

    private val queries = Channel<String>()
    val state = MutableStateFlow<ScreenState>(ScreenState.Initial)

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
        scope.launch {
            queries.send(searchField.text.trim())
        }
    }

    fun show() {
        mainFrame.isVisible = true
    }

    init {

        queries.consumeAsFlow()
            .onEach {
                state.emit(ScreenState.Initial)
            }.debounce(500)
            .map {
                if (it.isEmpty()){
                    state.emit(ScreenState.Initial)
                }else{
                    val result = repository.loadingDefinition(it)
                    if (result.isEmpty()){
                        state.emit(ScreenState.NotFound)
                    }else{
                        state.emit(ScreenState.DefinitionsLoaded(result))
                    }
                }
            }.launchIn(scope)


        state.onEach {
            when(it){
                is ScreenState.DefinitionsLoaded -> {
                    searchButton.isEnabled = true
                    resultArea.text = it.definition.joinToString("\n\n")
                }
                ScreenState.Initial -> {
                    searchButton.isEnabled = false
                    resultArea.text = ""
                }
                ScreenState.Loading -> {
                    searchButton.isEnabled = false
                    resultArea.text = "Searching the word...."
                }
                ScreenState.NotFound -> {
                    searchButton.isEnabled = true
                    resultArea.text = "Not Found"
                }
            }
        }.launchIn(scope)

    }

}


fun main() {
    Display.show()
    CoroutineScope(Dispatchers.IO).launch {
        delay(10000)
        println("Second subscriber")
        Display.state.collect {
            println(it)
        }
    }
}