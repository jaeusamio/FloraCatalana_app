package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Nomenclature(
    @SerialName("catalan_names")
    val catalanNames: List<String> = listOf(),
    @SerialName("english_names")
    val englishNames: List<String> = listOf(),
    @SerialName("french_names")
    val frenchNames: List<String> = listOf(),
    @SerialName("occitan_names")
    val occitanNames: List<String> = listOf(),
    @SerialName("scientific_name")
    val scientificName: String? = null,
    @SerialName("spanish_names")
    val spanishNames: List<String> = listOf(),
    @SerialName("synonyms")
    val synonyms: List<String> = listOf(),
    @SerialName("termcat_url")
    val termcatUrl: String? = ""
)