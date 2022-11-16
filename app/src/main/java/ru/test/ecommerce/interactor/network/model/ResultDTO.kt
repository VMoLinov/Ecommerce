package ru.test.ecommerce.interactor.network.model

data class ResultDTO(
    val home_store: List<HomeStoreDTO>,
    val best_seller: List<BestSellerDTO>
)
