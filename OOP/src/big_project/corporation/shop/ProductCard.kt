package big_project.corporation.shop

import big_project.corporation.enums.ProductType

abstract class ProductCard(
    val name: String,
    val brand: String,
    val price: Int,
    val productType: ProductType
) {

    abstract fun printInfo()

}
