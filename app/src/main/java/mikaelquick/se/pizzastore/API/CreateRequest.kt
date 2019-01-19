package mikaelquick.se.pizzastore.API

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface CreateRequest {

    companion object {
        const val BASE_URL = "https://private-anon-da4d000d94-pizzaapp.apiary-mock.com/"
    }

    @GET("https://private-anon-da4d000d94-pizzaapp.apiary-mock.com/")
    fun getProgramsByChannelAndCategory(): String

    @GET("restaurants/")
    fun getResturants(): Call<JsonArray>
}