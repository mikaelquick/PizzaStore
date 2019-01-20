package mikaelquick.se.pizzastore

import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mikaelquick.se.pizzastore.API.API
import mikaelquick.se.pizzastore.API.getResturants
import mikaelquick.se.pizzastore.Adapters.ResturangsAdapter
import mikaelquick.se.pizzastore.Utils.MyLocation

class List() : Fragment() {
    val TAG = "LIST"
    var adapter: ResturangsAdapter? =null
    lateinit var listView: View
    var currentLocation: Location? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!(::listView.isInitialized)) {
            initList(inflater,container)
            activity?.let {activityChecked->
                val myLocation = MyLocation(activityChecked)

                try {
                    currentLocation = myLocation.getCurretLocation()
                    addResturantsToList()
                }
                catch (e:Exception){
                    Log.e("MAIN",e.message)
                }
        }
        }
        return listView
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.e(TAG,"RESULT")
        addResturantsToList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.e(TAG,"RESULT")
        super.onActivityResult(requestCode, resultCode, data)
        Log.e(TAG,"RESULT")
    }



    fun addResturantsToList(){
        GlobalScope.launch {
            try{
                val resturants = API.getResturants()
                activity?.runOnUiThread {
                    adapter?.setItems(resturants.toList(),currentLocation)

                }
            }
            catch (e:Exception){
                Log.e(TAG,e.message)
            }
        }
    }

    fun initList(inflater: LayoutInflater,container: ViewGroup?){
        adapter = ResturangsAdapter()
        listView = inflater.inflate(R.layout.list, container, false)
        val rec = listView.findViewById<RecyclerView>(R.id.resturangList)
        rec.layoutManager = LinearLayoutManager(context)
        rec.setAdapter(adapter)
    }
}