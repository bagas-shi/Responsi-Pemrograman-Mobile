package com.pemmob.digimon.data.model

data class Digimon(
    val id: Int,
    val name: String,
    val images: List<DigimonImage>? = null,
    val levels: List<DigimonLevel>? = null,
    val attributes: List<DigimonAttribute>? = null,
    val types: List<DigimonType>? = null
) {

    val level: String
        get() = levels?.firstOrNull()?.level ?: "-"

    val attribute: String
        get() = attributes?.firstOrNull()?.attribute ?: "-"

    val type: String
        get() = types?.firstOrNull()?.type ?: "-"

    val image: String?
        get() = images?.firstOrNull()?.href
}

data class DigimonImage(
    val href: String,
    val transparent: Boolean
)

data class DigimonLevel(
    val id: Int,
    val level: String
)

data class DigimonAttribute(
    val id: Int,
    val attribute: String
)

data class DigimonType(
    val id: Int,
    val type: String
)