package big_project.corporation.shop

import big_project.corporation.enums.ProductType

class FoodCard(
    name: String,
    brand: String,
    price: Int,
    val caloric: Int
) : ProductCard(name, brand, price, ProductType.FOOD) {

    override fun printInfo() {
        print("Name: ${this.name} Brand: ${this.brand} Price: ${this.price} Product type: ${productType.title} Caloric: $caloric\n")
    }
}