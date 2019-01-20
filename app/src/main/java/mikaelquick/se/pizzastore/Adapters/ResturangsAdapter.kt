package mikaelquick.se.pizzastore.Adapters

import android.location.Location
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mikaelquick.se.pizzastore.API.Resturant
import mikaelquick.se.pizzastore.R


class ResturangsAdapter: RecyclerView.Adapter<ResturangsAdapter.ResturangViewHolder>() {
    private var items = mutableListOf<Resturant>()
    private var location: Location? = null

    override fun onCreateViewHolder(parent: ViewGroup, resource: Int): ResturangViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.resturang_item , parent, false)
        return ResturangViewHolder(view)
    }

    fun setItems(items: List<Resturant>,location: Location?) {
        this.items.clear()
        this.items.addAll(items)
        location?.let {
            this.location = it
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ResturangViewHolder, postion: Int) {
        val item = items[postion]

        holder.titleView.text = item.name
        Log.e("item",item.location?.latitude.toString())
        holder.distanceText.text = location?.distanceTo(item.location).toString()
    }


   class ResturangViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.resturang_title)
        var distanceText: TextView = itemView.findViewById(R.id.resturang_distance)
    }
}