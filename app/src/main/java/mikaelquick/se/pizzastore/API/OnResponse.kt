package mikaelquick.se.pizzastore.API

import android.util.Log
import com.google.gson.Gson
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun API.Companion.getResurant():String{

    return suspendCoroutine { cor->
        try {
            val result = service.getResturants().execute()
            val parsed = gson.toJson(result.body())
            cor.resume(parsed)
        }
        catch (e:Exception){
            cor.resumeWithException(e)
        }
    }

}