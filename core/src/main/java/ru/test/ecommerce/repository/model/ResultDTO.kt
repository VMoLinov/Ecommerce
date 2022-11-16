package ru.test.ecommerce.repository.model

data class ResultDTO(
    val home_store: List<HomeStoreDTO>,
    val best_seller: List<BestSellerDTO>
)
