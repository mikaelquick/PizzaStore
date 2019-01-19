package mikaelquick.se.pizzastore

import android.content.Context
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

class List : Fragment() {
    val TAG = "LIST"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.list, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val resturang =  activity?.findViewById<RecyclerView>(R.id.resturangList)
        resturang?.layoutManager = LinearLayoutManager(context)
        GlobalScope.launch {
            try{
                val resturants = API.getResturants()
                Log.d(TAG,resturants.get(0).id)
            }
            catch (e:Exception){
                Log.e(TAG,e.message)
            }
        }
    }
}