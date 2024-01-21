package com.floracatalana.floracatalana.domain.repository

import com.floracatalana.floracatalana.domain.model.Family
import com.floracatalana.floracatalana.domain.model.Genus
import com.floracatalana.floracatalana.domain.model.species.Species

interface SpeciesRepository {
    suspend fun loadSpecies(): List<Species>

    suspend fun loadGenera(): List<Genus>

    suspend fun loadFamilies(): List<Family>
}