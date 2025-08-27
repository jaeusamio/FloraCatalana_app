package com.floracatalana.floracatalana.data.remote.dto

import com.floracatalana.floracatalana.data.remote.dto.detail_response.FeedsItem
import com.floracatalana.floracatalana.data.remote.dto.detail_response.FieldImatge
import com.floracatalana.floracatalana.data.remote.dto.detail_response.FieldProcessed
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
data class FamilyDetailResponse(
    val body: List<FieldValueString>,
    val changed: List<Timestamp>,
    val created: List<Timestamp>,
    val default_langcode: List<FieldValueBoolean>,
    val feeds_item: List<FeedsItem>,
    val field_bibliografiafam: List<FieldUri>,
    val field_botanicsfam: List<FieldUri>,
    val field_clau_dicotomica: List<FieldUri>,
    val field_codi2: List<FieldValueString>,
    val field_col_laboradorsfam: List<FieldUri>,
    val field_comentaris_sobre_el_nom_de: List<FieldUri>,
    val field_distribucio: List<FieldProcessed>,
    val field_distribucio_geograficafam: List<FieldUri>,
    val field_en: List<FieldUri>,
    val field_etimologiafam: List<FieldValueString>,
    val field_flor_inflorescenciafam: List<FieldProcessed>,
    val field_fruit_llavorfam: List<FieldProcessed>,
    val field_fullesfam: List<FieldProcessed>,
    val field_generes1: List<FieldUri>,
    val field_habitatfam: List<FieldProcessed>,
    val field_imatge1: List<FieldImatge>,
    val field_imatge2: List<FieldImatge>,
    val field_imatge3: List<FieldImatge>,
    val field_imatge4: List<FieldImatge>,
    val field_imatge5: List<FieldImatge>,
    val field_imgfam6: List<FieldImatge>,
    val field_nom_cientific_amb_autorfam: List<FieldValueString>,
    val field_nom_de_la_familia1: List<FieldValueString>,
    val field_ordre1: List<FieldValueString>,
    val field_portfam: List<FieldProcessed>,
    val field_pronunciaci: List<FieldValueString>,
    val field_relacions_filogenetiques: List<FieldProcessed>,
    val field_subterranifam: List<FieldProcessed>,
    val field_taxons1: List<FieldUri>,
    val field_tija_troncfam: List<FieldProcessed>,
    val field_txtimatge1: List<FieldProcessed>,
    val field_txtimatge2: List<FieldProcessed>,
    val field_txtimatge3: List<FieldProcessed>,
    val field_txtimatge4: List<FieldProcessed>,
    val field_txtimatge5: List<FieldProcessed>,
    val field_txtimatge6: List<FieldProcessed>,
    val field_usos: List<FieldProcessed>,
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