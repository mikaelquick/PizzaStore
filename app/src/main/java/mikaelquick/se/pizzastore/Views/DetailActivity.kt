package mikaelquick.se.pizzastore.Views

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.Window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mikaelquick.se.pizzastore.API.API
import mikaelquick.se.pizzastore.API.getMenu
import mikaelquick.se.pizzastore.Adapters.CategoryAdapter
import mikaelquick.se.pizzastore.R

class DetailActivity : AppCompatActivity() {

    lateinit var adapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initList()
        GlobalScope.launch {
            try {
                API.getMenu(1)
            }catch (e:Exception){
                Log.e("DEAL",e.message)
            }

        }
    }

    fun initList(){
        adapter = CategoryAdapter(this)
        val rec = findViewById<RecyclerView>(R.id.category_container)
        rec.layoutManager = LinearLayoutManager(this)
        rec.setAdapter(adapter)
    }

    fun onClose(v: View){
        finish()
    }
}
