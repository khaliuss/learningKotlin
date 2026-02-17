package big_project.corporation

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
