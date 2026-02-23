package big_project.corporation.shop

import big_project.corporation.enums.ProductType

data class ShoeCard(
    override val name: String,
    override val brand: String,
    override val price: Int,
    val size: Float
) : ProductCard(name, brand, price, ProductType.SHOE) {


}