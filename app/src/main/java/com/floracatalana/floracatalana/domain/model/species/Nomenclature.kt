package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Nomenclature(
    val catalanNames: List<String> = listOf(),
    val englishNames: List<String> = listOf(),
    val frenchNames: List<String> = listOf(),
    val occitanNames: List<String> = listOf(),
    val scientificName: String? = null,
    val spanishNames: List<String> = listOf(),
    val synonyms: List<String> = listOf(),
    val termcatUrl: String? = ""
)