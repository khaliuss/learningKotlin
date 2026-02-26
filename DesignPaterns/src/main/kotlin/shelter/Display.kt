package shelter

import observers.Observer
import java.awt.Dimension
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class Display {

    fun show() {
        val textArea = JTextArea().apply {
            isEditable = false
            margin = Insets(32, 32, 32, 32)
        }

        val scrollable = JScrollPane(textArea)

        JFrame().apply {
            isVisible = true
            size = Dimension(600, 600)
            add(scrollable)
        }

        ShelterRepository.getInstance("qwerty").registerObserver(object: Observer<List<Dog>>{
            override fun onChange(newValue: List<Dog>) {
                textArea.text = newValue.joinToString ("\n")
            }
        })

    }

}