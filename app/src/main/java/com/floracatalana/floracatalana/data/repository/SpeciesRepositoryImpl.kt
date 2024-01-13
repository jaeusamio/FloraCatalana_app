package com.floracatalana.floracatalana.data.repository

import android.content.Context
import android.util.Log
import com.floracatalana.floracatalana.domain.model.Family
import com.floracatalana.floracatalana.domain.model.Genus
import com.floracatalana.floracatalana.domain.model.Species
import com.floracatalana.floracatalana.domain.repository.SpeciesRepository
import com.floracatalana.floracatalana.util.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.IOException
import javax.inject.Inject

class SpeciesRepositoryImpl @Inject constructor(private val context: Context): SpeciesRepository {
    override suspend fun loadSpecies(): List<Species> = withContext(Dispatchers.IO) {
        val speciesString = readJsonFromAssets("species.json")
        return@withContext Json.decodeFromString<List<Species>>(speciesString)
    }

    override suspend fun loadGenera(): List<Genus> = withContext(Dispatchers.IO) {
        val generaString = readJsonFromAssets("genera.json")
        return@withContext Json.decodeFromString<List<Genus>>(generaString)
    }

    override suspend fun loadFamilies(): List<Family> = withContext(Dispatchers.IO) {
        val familiesString = readJsonFromAssets("families.json")
        return@withContext Json.decodeFromString<List<Family>>(familiesString)
    }

    private suspend fun readJsonFromAssets(fileName: String): String = withContext(Dispatchers.IO) {
        val json = context.assets.open(fileName)
        return@withContext try {
            json.bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, "loadSpecies: could not read $fileName", ioException)
            "no va"
        }
    }
}