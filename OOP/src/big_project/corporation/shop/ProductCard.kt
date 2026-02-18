package big_project.corporation.shop

import big_project.corporation.enums.ProductType

open class ProductCard(
    val name: String,
    val brand: String,
    val price: Int,
    val productType: ProductType
) {

    open fun printInfo() {
        print("Name: ${this.name} Brand: ${this.brand} Price: ${this.price} Product type: ${productType.title}")
    }

}
