package mikaelquick.se.pizzastore.API

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API{

    companion object {
        val TAG = "API"
        val gson = Gson()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CreateRequest.BASE_URL)
            .build()
            .create(CreateRequest::class.java)
    }
}


