package com.example.feature_home_store.data.mapper

import com.example.core.utils.EntityMapper
import com.example.feature_home_store.data.remote.dto.HomeStoreDto
import com.example.feature_home_store.domain.models.HomeStore

class HomeStoreMapper: EntityMapper<HomeStoreDto, HomeStore> {
    override fun mapFromEntity(entity: HomeStoreDto): HomeStore {
        return HomeStore(
            id = entity.id,
            isBuy = entity.is_buy,
            isNew = entity.is_new,
            picture = entity.picture,
            subtitle = entity.subtitle,
            title = entity.title

        )
    }

    override fun mapToEntity(domainModel: HomeStore): HomeStoreDto {
        return HomeStoreDto(
            id = domainModel.id,
            is_buy = domainModel.isBuy,
            is_new = domainModel.isNew,
            picture = domainModel.picture,
            subtitle = domainModel.subtitle,
            title = domainModel.title
        )
    }

    fun toDomainList(initial: List<HomeStoreDto>): List<HomeStore>{
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<HomeStore>): List<HomeStoreDto>{
        return initial.map { mapToEntity(it) }
    }
}