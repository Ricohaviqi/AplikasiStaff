package com.example.actifitystaff

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }
    //retrofit
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("192.168.0.6")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(StaffService::class.java)
}
 interface StaffService{
     //fungsi create data
     @FormUrlEncoded
     @POST("addStaff")
     fun  addStaff(@Field("name") name: String,
                   @Field("hp") hp: String,
                   @Field("alamat") alamat: String) : Call<ResultStaff>

     //fungsi get data
     @GET("getDataStaff")
     fun getData() : Call<ResultStaff>

     //fungsi update data
     @FormUrlEncoded
     @POST("updateStaff")
     fun updateStaff(@Field("id") id: String,
                     @Field("name") name: String,
                     @Field("hp") hp: String,
                     @Field("alamat") alamat: String) : Call<ResultStaff>

     //fungsi delete
     @FormUrlEncoded
     @POST("deleteStaff")
     fun deleteStaff(@Field("id") id: String?) : Call<ResultStatus>


 }