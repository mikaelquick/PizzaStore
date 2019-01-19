package mikaelquick.se.pizzastore.API

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API{

    inline fun <reified T>json2Object(json:String): T{
        return gson.fromJson(json, T::class.java)
    }

    companion object {
        inline fun <reified T>json2Object(json:String): T{
           return gson.fromJson(json, T::class.java)
        }
        val TAG = "API"
        val gson = Gson()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CreateRequest.BASE_URL)
            .build()
            .create(CreateRequest::class.java)
    }
}


