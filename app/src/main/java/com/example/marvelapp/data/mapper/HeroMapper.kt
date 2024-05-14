package com.example.marvelapp.data.mapper

import com.example.marvelapp.data.model.dto.DtoHeroDataModel
import com.example.marvelapp.data.model.dto.DtoResultsModel
import com.example.marvelapp.data.model.entity.HeroEntity
import com.example.marvelapp.data.model.ui.UiResultsModel
import com.example.marvelapp.data.model.ui.UiThumbnailModel

class HeroDtoToUiMapper {
    fun map(dto: DtoResultsModel): UiResultsModel {
        return UiResultsModel(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            thumbnail = convertUrl(
                url = dto.thumbnail.path,
                extension = dto.thumbnail.extension
            )
        )
    }
}

class HeroEntityToUiMapper {
    fun map(entity: HeroEntity): UiResultsModel {
        return UiResultsModel(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            thumbnail = entity.thumbnail
        )
    }

    fun map(entities: List<HeroEntity>): List<UiResultsModel> {
        return entities.map { map(it) }
    }
}

class HeroDtoToEntityMapper {
    fun map(dto: DtoHeroDataModel): List<HeroEntity> {
        return dto.data.results.map {
            HeroEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnail = convertUrl(
                    url = it.thumbnail.path,
                    extension = it.thumbnail.extension
                )
            )
        }
    }
}

fun convertUrl(url: String, extension: String): String =
    "${url.replace("http", "https")}.$extension"