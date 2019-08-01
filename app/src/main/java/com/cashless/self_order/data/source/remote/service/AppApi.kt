package com.cashless.self_order.data.source.remote.service

import com.cashless.self_order.data.model.Token
import com.cashless.self_order.data.model.User
import com.cashless.self_order.data.source.remote.api.response.ApiResponse
import com.cashless.self_order.data.source.remote.api.response.RepoSearchResponse
import io.reactivex.Single
import retrofit2.http.*

/**
 * REST API access points
 */
interface AppApi {

  @POST("auth/login")
  @FormUrlEncoded
  fun doLogin(@Field("email") email: String, @Field("password") password: String): Single<Token>

  @GET("profile")
  fun getUserProfile(): Single<User>

  @GET("/search/repositories")
  fun searchRepository(@Query("q") query: String?, @Query(
      "page") page: Int): Single<RepoSearchResponse>

  @POST("password/forgot")
  @FormUrlEncoded
  fun forgetPassword(@Field("email") email: String): Single<Boolean>

  @PUT("profile")
  @FormUrlEncoded
  fun updateProfile(@Field("name") username: String): Single<ApiResponse<Any>>

}