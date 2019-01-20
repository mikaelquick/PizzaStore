package mikaelquick.se.pizzastore.API

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CreateRequest {

    companion object {
        const val BASE_URL = "https://private-anon-da4d000d94-pizzaapp.apiary-mock.com/"
    }

    @GET("restaurants/")
    fun getResturants(): Call<ArrayList<JsonObject>>

    @GET("/restaurants/{id}/menu")
    fun getMenu(@Path("id") id: Int): Call<ArrayList<JsonObject>>
}