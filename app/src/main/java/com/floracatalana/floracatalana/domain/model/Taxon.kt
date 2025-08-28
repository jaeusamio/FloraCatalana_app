package com.floracatalana.floracatalana.domain.model

interface Taxon{
    val code: String
    val nameLatin: String
    val nameCat: String?
    val rank: TaxonRank
    val nodeId: String
    val url: String
}