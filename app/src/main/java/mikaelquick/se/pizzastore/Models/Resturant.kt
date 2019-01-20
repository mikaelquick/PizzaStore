package mikaelquick.se.pizzastore.API

import android.location.Location
import android.util.Log


class Resturant(){
    val id: Int = 0
    val name: String = ""
    val latitude: Double = 0.0
    val longitude: Double = 0.0
    var distanceFromMe: Float? = null
    val location: Location?
    get() {
        return convert2Location(latitude,longitude)
    }

    private fun convert2Location(lat: Double,long:Double): Location{
        val location = Location("")
        location.latitude = lat
        location.longitude = long
        return location
    }
}