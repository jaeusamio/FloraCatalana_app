package com.floracatalana.floracatalana.data.paging.factory

import androidx.paging.Pager
import com.floracatalana.floracatalana.data.remote.dto.GenusListResponse

fun interface GenusPagerFactory {
    fun create(familyCode: String?): Pager<Int, GenusListResponse>
}