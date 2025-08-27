package com.floracatalana.floracatalana.data.remote.dto

import com.floracatalana.floracatalana.data.remote.dto.detail_response.FeedsItem
import com.floracatalana.floracatalana.data.remote.dto.detail_response.FieldTargetItem
import com.floracatalana.floracatalana.data.remote.dto.detail_response.FieldUri
import com.floracatalana.floracatalana.data.remote.dto.detail_response.FieldValueBoolean
import com.floracatalana.floracatalana.data.remote.dto.detail_response.FieldValueInt
import com.floracatalana.floracatalana.data.remote.dto.detail_response.FieldValueString
import com.floracatalana.floracatalana.data.remote.dto.detail_response.Path
import com.floracatalana.floracatalana.data.remote.dto.detail_response.Timestamp
import com.floracatalana.floracatalana.data.remote.dto.detail_response.Type
import kotlinx.serialization.Serializable

@Serializable
data class GenusDetailResponse(
    val body: List<FieldValueString>,
    val changed: List<Timestamp>,
    val created: List<Timestamp>,
    val default_langcode: List<FieldValueBoolean>,
    val feeds_item: List<FeedsItem>,
    val field_codi4: List<FieldValueString>,
    val field_enlla: List<FieldUri>,
    val field_fam: List<FieldTargetItem>,
    val field_img_altres: List<FieldUri>,
    val field_img_flor_infl_: List<FieldUri>,
    val field_img_fruit_llavor: List<FieldUri>,
    val field_img_fulles: List<FieldUri>,
    val field_img_habitat: List<FieldUri>,
    val field_img_port: List<FieldUri>,
    val field_img_subterrani: List<FieldUri>,
    val field_img_tija_tronc: List<FieldUri>,
    val field_nom_del_genere1: List<FieldValueString>,
    val field_pronunciacio6: List<FieldValueString>,
    val field_taxons_finals: List<FieldUri>,
    val langcode: List<FieldValueString>,
    val nid: List<FieldValueInt>,
    val path: List<Path>,
    val promote: List<FieldValueBoolean>,
    val revision_timestamp: List<Timestamp>,
    val revision_translation_affected: List<FieldValueBoolean>,
    val revision_uid: List<FieldTargetItem>,
    val status: List<FieldValueBoolean>,
    val sticky: List<FieldValueBoolean>,
    val title: List<FieldValueString>,
    val type: List<Type>,
    val uid: List<FieldTargetItem>,
    val uuid: List<FieldValueString>,
    val vid: List<FieldValueInt>
)