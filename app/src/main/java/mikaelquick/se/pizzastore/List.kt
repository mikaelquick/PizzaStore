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
import mikaelquick.se.pizzastore.Adapters.ResturangsAdapter

class List : Fragment() {
    val TAG = "LIST"
    lateinit var listView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!(::listView.isInitialized)) {
            val adapter = ResturangsAdapter()
            listView = inflater.inflate(R.layout.list, container, false)
            val rec = listView.findViewById<RecyclerView>(R.id.resturangList)
            rec.layoutManager = LinearLayoutManager(context)
            rec.setAdapter(adapter)
            GlobalScope.launch {
                try{
                    val resturants = API.getResturants()
                    activity?.runOnUiThread {
                        adapter.setItems(resturants.toList())

                    }

                    Log.d(TAG,resturants.get(0).id)
                }
                catch (e:Exception){
                    Log.e(TAG,e.message)
                }
            }
        }
        return listView
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)



    }
}