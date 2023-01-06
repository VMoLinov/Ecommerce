package ru.test.domain.detailsscreen.model

data class DeviceDetails(
    val CPU: String,
    val camera: String,
    val specs: List<DetailsListItem>,
    val id: String,
    val images: List<DetailsListItem>,
    val isFavorites: Boolean,
    val price: String,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String
)
