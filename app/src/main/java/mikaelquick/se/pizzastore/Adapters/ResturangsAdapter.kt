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

import mikaelquick.se.pizzastore.API.API
import mikaelquick.se.pizzastore.API.Resturant
import mikaelquick.se.pizzastore.API.getResturants
import android.R


class ResturangsAdapter: RecyclerView.Adapter<ResturangsAdapter.PlayableViewHolder>() {
    private var items = mutableListOf<Resturant>()

    override fun onCreateViewHolder(parent: ViewGroup, postion: Int): PlayableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(postion, parent, false)
        return PlayableViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.simple_list_item_single_choice
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(p0: PlayableViewHolder, p1: Int) {

    }


    class PlayableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}

class PlayableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}
