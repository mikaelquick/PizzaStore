package mikaelquick.se.pizzastore.API

import android.util.Log
import android.view.Menu
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import mikaelquick.se.pizzastore.Models.MenuObjects
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

suspend fun API.Companion.getMenu(id:Int):Array<MenuObjects>{

    return suspendCoroutine { cor->
        try {
            val result =  service.getMenu(id).execute()
            Log.e(TAG,result.body().toString())
            val obj = API.json2Object<Array<MenuObjects>>(result.body().toString())
            obj.first().topping?.forEach {
                Log.e(TAG,it)
            }

           cor.resume(obj)
        }
        catch (e:Exception){
            Log.e(TAG,e.message)
            cor.resumeWithException(e)
        }
    }
}


