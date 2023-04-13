package com.android.e_mart.model

class Product(
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>?,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String?,
    val title: String?
) {
    data class Builder(
        var brand: String? = null,
        var category: String? = null,
        var description: String? = null,
        var discountPercentage: Double = 0.0,
        var id: Int = 0,
        var images: List<String>? = null,
        var price: Int = 0,
        var rating: Double = 0.0,
        var stock: Int = 0,
        var thumbnail: String? = null,
        var title: String? = null
    ) {

        fun brand(brand: String) = apply { this.brand = brand }
        fun category(category: String) = apply { this.category = category }
        fun description(description: String) = apply { this.description = description }
        fun discountPercentage(discountPercentage: Double) =
            apply { this.discountPercentage = discountPercentage }

        fun id(id: Int) = apply { this.id = id }
        fun images(images: List<String>) = apply { this.images = images }
        fun price(price: Int) = apply { this.price = price }
        fun rating(rating: Double) = apply { this.rating = rating }
        fun stock(stock: Int) {
            this.stock = stock
        }

        fun thumbnail(thumbnail: String?) = apply { this.thumbnail = thumbnail }
        fun title(title: String) = apply { this.title = title }

        fun build() = Products(
            brand,
            category,
            description,
            discountPercentage,
            id,
            images,
            price,
            rating,
            stock,
            thumbnail,
            title
        )


    }

}