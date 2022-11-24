package com.example.feature_cart.data.mapper

import com.example.core.utils.EntityMapper
import com.example.feature_cart.data.remote.dto.CartDto
import com.example.feature_cart.domain.models.Cart
import javax.inject.Inject

class CartMapper @Inject constructor(private val mapper: CartInfoMapper): EntityMapper<CartDto, Cart> {
    override fun mapFromEntity(entity: CartDto): Cart {
        return Cart(
            id = entity.id,
            basket = mapper.toDomainList(entity.basket),
            delivery = entity.delivery,
            total = entity.total
        )
    }

    override fun mapToEntity(domainModel: Cart): CartDto {
        return CartDto(
            id = domainModel.id,
            basket = mapper.toEntityList(domainModel.basket),
            delivery = domainModel.delivery,
            total = domainModel.total
        )
    }

}