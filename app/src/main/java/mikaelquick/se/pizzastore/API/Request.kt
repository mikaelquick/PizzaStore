package mikaelquick.se.pizzastore.API

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun API.Companion.getResturants():Array<Resturant>{

    return suspendCoroutine { cor->
        try {
            val result =  service.getResturants().execute()
            Log.e(TAG,result.body().toString())
            val obj = API.json2Object<Array<Resturant>>(result.body().toString())
            cor.resume(obj)
        }
        catch (e:Exception){
            Log.e(TAG,e.message)
            cor.resumeWithException(e)
        }
    }
}

suspend fun API.Companion.getMenu(id:Int):Array<Resturant>{

    return suspendCoroutine { cor->
        try {
            val result =  service.getMenu(id).execute()
            Log.e(TAG,result.body().toString())
          //  val obj = API.json2Object<Array<Resturant>>(result.body().toString())
           // cor.resume(obj)
        }
        catch (e:Exception){
            Log.e(TAG,e.message)
            cor.resumeWithException(e)
        }
    }
}


