package V4.home

class Car {

    var brand: String = ""
    var model: String = ""
    var enginePower: Int = 0;
    var bodyColor: String = ""

    fun drive(){
        print("I'm driving")
    }

    fun refuel( gasStation:String, gasolineBrande: String,liters:Int){
        print("Произведена заправка на АЗС \"$gasStation\"\n" +
                "Марка бензина: $gasolineBrande\n" +
                "Кол-во литров: $liters")

    }


}