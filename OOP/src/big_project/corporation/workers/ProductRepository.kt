package big_project.corporation.workers

import big_project.corporation.enums.ProductType
import big_project.corporation.shop.ApplianceCard
import big_project.corporation.shop.FoodCard
import big_project.corporation.shop.ProductCard
import big_project.corporation.shop.ShoeCard
import java.io.File

class ProductRepository {

    private val productFile = File("product_card.txt")
    val products = getAllProducts()


    private fun getAllProducts(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()
        if (!productFile.exists()) productFile.createNewFile()
        val allText = productFile.readText().trim()
        val lines = allText.split("\n")

        for (line in lines) {
            val properties = line.split("%")
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()

            val productType = ProductType.valueOf(properties.last())

            val product = when (productType) {
                ProductType.FOOD -> {
                    val caloric = properties[3].toInt()
                    FoodCard(name, brand, price, caloric)
                }

                ProductType.APPLIANCE -> {
                    val wattage = properties[3].toInt()
                    ApplianceCard(name, brand, price, wattage)
                }

                ProductType.SHOE -> {
                    val size = properties[3].toFloat()
                    ShoeCard(name, brand, price, size)
                }
            }
            cards.add(product)
        }

        return cards
    }

    fun saveProduct(productCard: ProductCard){
        products.add(productCard)
    }

    fun saveChanges() {
        val allInOneStr = StringBuilder()
        for (product in products){
            allInOneStr.append("${product.name}%${product.brand}%${product.price}%")
            when (product) {
                is FoodCard -> {
                    allInOneStr.append("${product.caloric}%")
                }

                is ApplianceCard -> {
                    allInOneStr.append("${product.wattage}%")
                }

                is ShoeCard -> {
                    allInOneStr.append("${product.size}%")
                }
            }
            allInOneStr.append("${product.productType}\n")
        }
        productFile.writeText(allInOneStr.toString())
    }


    fun removeProductCard(name:String) {
        for (product in products){
            if (product.name == name){
                products.remove(product)
            }
        }
    }

}