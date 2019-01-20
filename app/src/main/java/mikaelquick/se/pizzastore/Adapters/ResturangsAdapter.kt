package mikaelquick.se.pizzastore.Adapters

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
import kotlinx.android.synthetic.main.resturang_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mikaelquick.se.pizzastore.API.API
import mikaelquick.se.pizzastore.API.Resturant
import mikaelquick.se.pizzastore.API.getResturants
import mikaelquick.se.pizzastore.Adapters.ResturangsAdapter
import mikaelquick.se.pizzastore.R


class ResturangsAdapter: RecyclerView.Adapter<ResturangsAdapter.PlayableViewHolder>() {
    private var items = mutableListOf<Resturant>()

    override fun onCreateViewHolder(parent: ViewGroup, resource: Int): PlayableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.resturang_item , parent, false)
        return PlayableViewHolder(view)
    }

    fun setItems(items: List<Resturant>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: PlayableViewHolder, p1: Int) {

    }


    class PlayableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}

class PlayableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}
