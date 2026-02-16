package big_project;

class ProductCard(
    val name: String,
    val brand: String,
    val size: Float,
    val price: Int
) {

    fun printInfo() {
        println("Name: ${this.name} Brand: ${this.brand} Size: ${this.size} Price: ${this.price}")
    }

}
