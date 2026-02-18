package big_project.corporation.shop

import big_project.corporation.enums.ProductType

class FoodCard(
    name: String,
    brand: String,
    price: Int,
    val caloric: Int
) : ProductCard(name, brand, price, ProductType.FOOD) {

    override fun toString(): String {
        return "Name='$name', Brand='$brand', Price=$price, ProductType=${productType.title}, Caloric=$caloric\n"
    }


}