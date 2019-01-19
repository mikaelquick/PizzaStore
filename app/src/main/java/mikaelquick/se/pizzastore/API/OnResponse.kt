package mikaelquick.se.pizzastore.API

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun API.Companion.getResurant():String{

    return suspendCoroutine { cor->
        try {
            val result =  service.getResturants().execute()
Log.e(TAG,result.body().toString())


            val obj2 = gson.fromJson(result.body().toString(), Array<Resturant>::class.java)

            val test1 = API.json2Object<Array<Resturant>>(result.body().toString())
            Log.e(TAG,test1.get(0).id)

            //cor.resume(parsed)
        }
        catch (e:Exception){
            Log.e(TAG,e.message)
            cor.resumeWithException(e)
        }
    }

}

class Resturants(){
    val resturants: ArrayList<Resturant>? = null
}

class Resturant(){
    val id: String = ""
}