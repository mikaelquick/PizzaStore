package mikaelquick.se.pizzastore.Adapters

import android.content.Context
import android.content.Intent
import android.location.Location
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mikaelquick.se.pizzastore.API.Resturant
import mikaelquick.se.pizzastore.Views.DetailActivity
import mikaelquick.se.pizzastore.R

class CategoryAdapter(val context: Context): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onBindViewHolder(p0: CategoryViewHolder, p1: Int) {
    }

    private var items = mutableListOf<Resturant>()

    fun setItems(inItems: List<Resturant>, location: Location?) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CategoryAdapter.CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_item , parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }



    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}