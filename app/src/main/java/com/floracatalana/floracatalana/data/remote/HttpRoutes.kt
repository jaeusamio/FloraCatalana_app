package com.floracatalana.floracatalana.data.remote

object HttpRoutes {

    const val BASE_URL = "https://floracatalana.cat"

    const val SPECIES_LIST = "$BASE_URL/flora/vasculars/apifc/taxonsfinalscodi?_format=json"
    const val SPECIES_SEARCH = "$BASE_URL/flora/vasculars/apifc/taxonsfinalscodi?_format=json"
    const val GENUS_LIST = "$BASE_URL/flora/vasculars/apifc/generescodi?_format=json"
    const val FAMILY_LIST = "$BASE_URL/flora/vasculars/apifc/familiescodi?_format=json"
    object SPECIES_DETAIL {
        fun passCode(code: String) = "$BASE_URL/flora/vasculars/taxons/$code?_format=json"
    }
    object GENUS_DETAIL {
        fun passCode(code: String) = "$BASE_URL/flora/node/$code?_format=json"
    }
    object FAMILY_DETAIL {
        fun passCode(code: String) = "$BASE_URL/flora/node/$code?_format=json"
    }
}