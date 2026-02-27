package users

import observers.Observer
import java.awt.Dimension
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class Display{



    var frame: JFrame? = null

    fun show(){

        val textArea = JTextArea().apply {
            isEditable = false
            margin = Insets(32,32,32,32)
        }

        val scroll = JScrollPane(textArea)

         frame =JFrame().apply {
            isVisible = true
            isResizable = false
            size = Dimension(600,600)
            add(scroll)
        }

        UserRepository.getInstance("qwerty").registerObserver {
            textArea.text = it.joinToString("\n")
        }

    }






}