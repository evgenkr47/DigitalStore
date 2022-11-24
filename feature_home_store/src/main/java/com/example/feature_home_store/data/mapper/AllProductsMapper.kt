package com.example.feature_home_store.data.mapper

import com.example.core.utils.EntityMapper
import com.example.feature_home_store.data.remote.dto.AllProductsDto
import com.example.feature_home_store.domain.models.AllProducts
import javax.inject.Inject

class AllProductsMapper @Inject constructor(
    private val homeStoreMapper: HomeStoreMapper,
    private val bestSellerMapper: BestSellerMapper
    ): EntityMapper<AllProductsDto, AllProducts> {
    override fun mapFromEntity(entity: AllProductsDto): AllProducts {
        return AllProducts(
            bestSeller = bestSellerMapper.toDomainList(entity.best_seller),
            homeStore = homeStoreMapper.toDomainList(entity.home_store)
        )
    }

    override fun mapToEntity(domainModel: AllProducts): AllProductsDto {
        return AllProductsDto(
            best_seller = bestSellerMapper.toEntityList(domainModel.bestSeller),
            home_store = homeStoreMapper.toEntityList(domainModel.homeStore)
        )
    }
}