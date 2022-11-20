package ru.test.network.model

data class ResultDTO(
    val home_store: List<HomeStoreDTO>,
    val best_seller: List<BestSellerDTO>
)
