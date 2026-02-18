package big_project.corporation.shop

import big_project.corporation.enums.ProductType

class ShoeCard(
    name: String,
    brand: String,
    price: Int,
    val size: Float
) : ProductCard(name, brand, price, ProductType.SHOE) {

    override fun printInfo() {
        print("Name: ${this.name} Brand: ${this.brand} Price: ${this.price} Product type: ${productType.title} Size: $size\n")
    }
}