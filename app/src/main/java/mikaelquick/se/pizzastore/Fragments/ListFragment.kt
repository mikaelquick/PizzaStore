package mikaelquick.se.pizzastore.Fragments

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mikaelquick.se.pizzastore.API.API
import mikaelquick.se.pizzastore.API.getResturants
import mikaelquick.se.pizzastore.Adapters.ResturangsAdapter
import mikaelquick.se.pizzastore.Utils.MyLocation
import mikaelquick.se.pizzastore.R

class ListFragment() : Fragment() {
    val TAG = "LIST"
    var adapter: ResturangsAdapter? =null
    lateinit var listView: View
    var currentLocation: Location? = null
    var myLocation: MyLocation? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!(::listView.isInitialized)) {
            initList(inflater,container)
                    addResturantsToList(updateLocation = true)
        }
        return listView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity?.let {activityChecked->
            myLocation = MyLocation(activityChecked)
        }
    }

    fun addResturantsToList(updateLocation:Boolean = false) {
        if (updateLocation) {
            try {
                currentLocation = myLocation?.getCurretLocation()
                downloadAndSet()
            } catch (e: Exception) {
                Log.e(TAG, e.message)
            }
        } else {
            downloadAndSet()
        }
    }

   private fun downloadAndSet(){
        GlobalScope.launch {
            try{
                val resturants = API.getResturants().toList()
                activity?.runOnUiThread {
                    adapter?.setItems(resturants,currentLocation)
                }
            }
            catch (e:Exception){
                Log.e(TAG,e.message)
            }
        }
    }

   private fun initList(inflater: LayoutInflater,container: ViewGroup?){
        context?.let {contextChecked->
            adapter = ResturangsAdapter(contextChecked)
            listView = inflater.inflate(R.layout.list, container, false)
            val rec = listView.findViewById<RecyclerView>(R.id.resturangList)
            rec.layoutManager = LinearLayoutManager(contextChecked)
            rec.setAdapter(adapter)
        }
    }
}