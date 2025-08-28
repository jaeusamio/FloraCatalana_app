package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Description(
    val lifeForm: String? = null,
    val size: Size = Size(),
    val sections: List<DescriptionSection> = emptyList()
)