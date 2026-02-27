package users.temp



import java.awt.Dimension
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class Display2{



    fun show(){

        val textArea = JTextArea().apply {
            isEditable = false
            margin = Insets(32,32,32,32)
        }

        val scroll = JScrollPane(textArea)

         JFrame().apply {
            isVisible = true
            isResizable = false
            size = Dimension(600,600)
            add(scroll)
        }

        UserRepository2.getInstance("qwerty").registerObserver(){
            textArea.text = it.joinToString("\n")
        }



    }






}