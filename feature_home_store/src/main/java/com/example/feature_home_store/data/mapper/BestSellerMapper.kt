package com.example.feature_home_store.data.mapper

import com.example.core.utils.EntityMapper
import com.example.feature_home_store.data.remote.dto.BestSellerDto
import com.example.feature_home_store.domain.models.BestSeller
import javax.inject.Inject

class BestSellerMapper @Inject constructor(): EntityMapper<BestSellerDto, BestSeller> {
    override fun mapFromEntity(entity: BestSellerDto): BestSeller {
        return BestSeller(
            id = entity.id,
            discountPrice = entity.discount_price,
            picture = entity.picture,
            priceWithoutDiscount = entity.price_without_discount,
            title = entity.title,
            isFavorites = entity.is_favorites
        )
    }

    override fun mapToEntity(domainModel: BestSeller): BestSellerDto {
        return BestSellerDto(
            id = domainModel.id,
            discount_price = domainModel.discountPrice,
            picture = domainModel.picture,
            price_without_discount = domainModel.priceWithoutDiscount,
            title = domainModel.title,
            is_favorites = domainModel.isFavorites
        )
    }

    fun toDomainList(initial: List<BestSellerDto>): List<BestSeller>{
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<BestSeller>): List<BestSellerDto>{
        return initial.map { mapToEntity(it) }
    }
}