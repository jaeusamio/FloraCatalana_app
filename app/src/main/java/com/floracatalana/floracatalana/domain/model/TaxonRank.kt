package com.floracatalana.floracatalana.domain.model

enum class TaxonRank(val id: Int, val label: String) {
    SPECIES(id = 0, label = "ESPÈCIE"),
    SPECIES_WITH_SUBSPECIES(id = 1, label = "ESPÈCIE AMB SUBESPÈCIE"),
    GENUS(id = 2, label = "GENÈRE"),
    FAMILY(id = 3, label = "FAMÍLIA"),
    SUBSPECIES(id = 4, label = "SUBESPÈCIE"),
    VARIETY(id = 5, label = "VARIETAT" ),
    HYBRID(id = 6, label = "HYBRID"),
    GROUP(id = 7, label = "GRUP")
}