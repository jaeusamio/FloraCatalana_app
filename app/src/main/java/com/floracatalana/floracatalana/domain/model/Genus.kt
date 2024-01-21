package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Genus(
    @SerialName("code")
    val code: String = "",
    @SerialName("short_family")
    val shortFamily: ShortTaxon = ShortTaxon(),
    @SerialName("n_species")
    val nSpecies: Int = 0,
    @SerialName("name_cat")
    val nameCat: String = "",
    @SerialName("name_latin")
    val nameLatin: String = "",
    @SerialName("node_id")
    val nodeId: String = "",
    @SerialName("rank")
    val rank: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("species")
    val species: List<ShortTaxon> = listOf()
)