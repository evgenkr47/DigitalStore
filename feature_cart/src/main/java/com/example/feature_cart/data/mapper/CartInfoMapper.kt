package com.example.feature_cart.data.mapper

import com.example.core.utils.EntityMapper
import com.example.feature_cart.data.remote.dto.CartInfoDto
import com.example.feature_cart.domain.models.CartInfo
import javax.inject.Inject

class CartInfoMapper @Inject constructor(): EntityMapper<CartInfoDto, CartInfo> {
    override fun mapFromEntity(entity: CartInfoDto): CartInfo {
        return CartInfo(
            id = entity.id,
            images = entity.images,
            price = entity.price,
            title = entity.title
        )
    }

    override fun mapToEntity(domainModel: CartInfo): CartInfoDto {
        return CartInfoDto(
            id = domainModel.id,
            images = domainModel.images,
            price = domainModel.price,
            title = domainModel.title
        )
    }

    fun toDomainList(initial: List<CartInfoDto>): List<CartInfo>{
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<CartInfo>): List<CartInfoDto>{
        return initial.map { mapToEntity(it) }
    }
}