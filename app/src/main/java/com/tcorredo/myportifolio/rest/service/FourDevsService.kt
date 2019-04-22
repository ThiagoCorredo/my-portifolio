package com.tcorredo.myportifolio.rest.service

import com.tcorredo.myportifolio.BuildConfig.FOUR_DEVS_FINAL_ENDPOINT
import com.tcorredo.myportifolio.model.User
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * @author Thiago Corredo
 * @since 2019-03-11
 */
interface FourDevsService {

    @Multipart
    @POST(FOUR_DEVS_FINAL_ENDPOINT)
    fun createRandomUser(
        @Part("acao") action: RequestBody, @Part("pontuacao") hasDots: RequestBody,
        @Part("cep_estado") zipState: RequestBody, @Part("cep_cidade") zipCity: RequestBody,
        @Part("sexo") gender: RequestBody, @Part("idade") age: RequestBody
    ): Single<User>

    @Multipart
    @POST(FOUR_DEVS_FINAL_ENDPOINT)
    fun findCities(
        @Part("acao") action: RequestBody, @Part("cep_estado") zipState: RequestBody
    ): Single<ResponseBody>
}