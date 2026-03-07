package second.executors

import java.util.concurrent.Executors

fun main() {
    val executor = Executors.newFixedThreadPool(1000)

    repeat(10_000){
        executor.execute {
            processImage(Image(it))
        }
    }
}

private fun processImage(img:Image){
    println("Image $img is processing")
    Thread.sleep(1000)
    println("Image $img processed")
}

data class Image(val id:Int)