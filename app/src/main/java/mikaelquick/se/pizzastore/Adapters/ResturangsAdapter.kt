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
import mikaelquick.se.pizzastore.R
import mikaelquick.se.pizzastore.Views.DetailActivity


class ResturangsAdapter(val context: Context): RecyclerView.Adapter<ResturangsAdapter.ResturangViewHolder>() {
    private var items = mutableListOf<Resturant>()

    override fun onCreateViewHolder(parent: ViewGroup, resource: Int): ResturangViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.resturang_item , parent, false)
        return ResturangViewHolder(view)
    }

    fun setItems(inItems: List<Resturant>, location: Location?) {
        this.items.clear()
        var items = inItems
        location?.let { current ->
            inItems.forEach { resturant ->
                current.distanceTo(resturant.location).let { orgDistance ->
                    resturant.distanceFromMe = orgDistance
                }
            }
            items = inItems.sortedWith(compareBy({ it.distanceFromMe }))
        }
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ResturangViewHolder, postion: Int) {
        val item = items[postion]

        item.distanceFromMe?.let {distance->
            holder.distanceText.text = styleDistanceText(distance)
        }
        holder.titleView.text = item.name

        holder.card.setOnClickListener({
            Log.e("ADAP","Click")
            val intent = Intent(context,DetailActivity::class.java)
            context.startActivity(intent)
        })
    }

    private fun styleDistanceText(orgDistance:Float):String{
        if(orgDistance< 1000){
            return "${String.format("%.0f",orgDistance)}m"
        }
        return "${String.format("%.0f",orgDistance/1000)}km"
    }

   class ResturangViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.resturang_title)
        var distanceText: TextView = itemView.findViewById(R.id.resturang_distance)
       var card: CardView = itemView.findViewById(R.id.card_container)
    }
}