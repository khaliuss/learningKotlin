package V4

class Assistant {

    fun bringCoffee(count:Int,coffeeType:String){
         repeat(count){
             println("Get up")
             println("Go to the coffee machine")
             println("Press the \"$coffeeType\" button")
             println("Wait for the $coffeeType to be prepared")
             println("Take coffee")
             println("Bring coffee to the director")
             println("Put coffee on the table")
             println("Return to the workplace")
         }

    }

}