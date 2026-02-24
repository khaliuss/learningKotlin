package products;

import org.example.products.Brand
import org.example.products.Condition
import org.example.products.ProductCard

class ProductBrandA : Condition {

    override fun isSuitable(productCard: ProductCard): Boolean {
        return productCard.brand == Brand.BRAND_A
    }

}
