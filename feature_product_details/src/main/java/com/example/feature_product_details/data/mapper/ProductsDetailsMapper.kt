package com.example.feature_product_details.data.mapper

import com.example.core.utils.EntityMapper
import com.example.feature_product_details.data.remote.dto.ProductDetailsDto
import com.example.feature_product_details.domain.models.ProductDetails
import javax.inject.Inject

class ProductsDetailsMapper @Inject constructor(): EntityMapper<ProductDetailsDto, ProductDetails> {
    override fun mapFromEntity(entity: ProductDetailsDto): ProductDetails {
        return ProductDetails(
            id = entity.id,
            CPU = entity.CPU,
            camera = entity.camera,
            capacity = entity.capacity,
            color = entity.color,
            images = entity.images,
            isFavorites = entity.isFavorites,
            price = entity.price,
            rating = entity.rating,
            sd = entity.sd,
            ssd = entity.ssd,
            title = entity.title
        )
    }

    override fun mapToEntity(domainModel: ProductDetails): ProductDetailsDto {
        return ProductDetailsDto(
            id = domainModel.id,
            CPU = domainModel.CPU,
            camera = domainModel.camera,
            capacity = domainModel.capacity,
            color = domainModel.color,
            images = domainModel.images,
            isFavorites = domainModel.isFavorites,
            price = domainModel.price,
            rating = domainModel.rating,
            sd = domainModel.sd,
            ssd = domainModel.ssd,
            title = domainModel.title
        )
    }
}