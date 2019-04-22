package com.tcorredo.myportifolio.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * @author Thiago Corredo
 * @since 2019-03-04
 */
@Parcelize
data class User(
    @Json(name = "nome")
    var name: String,

    @Json(name = "cpf")
    var cpf: String,

    @Json(name = "rg")
    var rg: String,

    @Json(name = "data_nasc")
    var birthday: String,

    @Json(name = "signo")
    var sign: String,

    @Json(name = "mae")
    var motherName: String,

    @Json(name = "pai")
    var fatherName: String,

    @Json(name = "email")
    var email: String,

    @Json(name = "senha")
    var password: String,

    @Json(name = "cep")
    var cep: String,

    @Json(name = "endereco")
    var street_name: String,

    @Json(name = "numero")
    var number: Long,

    @Json(name = "bairro")
    var district: String,

    @Json(name = "cidade")
    var city: String,

    @Json(name = "estado")
    var state: String,

    @Json(name = "telefone_fixo")
    var phone: String,

    @Json(name = "celular")
    var cellPhone: String,

    @Json(name = "altura")
    var height: String,

    @Json(name = "peso")
    var weight: Long,

    @Json(name = "tipo_sanguineo")
    var bloodType: String,

    @Json(name = "cor")
    var skinColor: String
) : Parcelable