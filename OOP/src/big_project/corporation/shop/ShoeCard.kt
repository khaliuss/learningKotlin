package big_project.corporation.shop

import big_project.corporation.enums.ProductType

class ShoeCard(
    name: String,
    brand: String,
    price: Int,
    val size: Float
) : ProductCard(name, brand, price, ProductType.SHOE) {


    override fun toString(): String {
        return "Name='$name', Brand='$brand', Price=$price, ProductType=${productType.title}, Size=$size\n"
    }


}