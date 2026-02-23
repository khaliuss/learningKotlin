package big_project.corporation.shop

import big_project.corporation.enums.ProductType

abstract class ProductCard(
    open val name: String,
    open val brand: String,
    open val price: Int,
    open val productType: ProductType
) {

    fun printInfo(){
        println(this)
    }
}
