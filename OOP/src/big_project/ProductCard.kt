package big_project;

class ProductCard {

    val name: String
    val brand: String
    val size: Float
    val price: Int

    constructor(name: String, brand: String, size: Float, price: Int) {
        this.name = name
        this.brand = brand
        this.size = size
        this.price = price
    }

    fun printInfo() {
        println("Name: ${this.name} Brand: ${this.brand} Size: ${this.size} Price: ${this.price}")
    }

}
