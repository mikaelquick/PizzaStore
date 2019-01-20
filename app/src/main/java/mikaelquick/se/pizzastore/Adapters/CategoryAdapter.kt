package mikaelquick.se.pizzastore.Adapters

import android.content.Context
import android.content.Intent
import android.location.Location
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import mikaelquick.se.pizzastore.API.Resturant
import mikaelquick.se.pizzastore.Models.MenuObjects
import mikaelquick.se.pizzastore.Views.DetailActivity
import mikaelquick.se.pizzastore.R

class CategoryAdapter(val context: Context): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var cat = ""

    override fun onBindViewHolder(holder: CategoryViewHolder, postion: Int) {
        val item = items[postion]
        holder.name.text = item.name
        holder.price.text = returnPrice(item)

        if(holder.amount.tag == null){
            holder.amount.tag = 0
        }

        if(item.category == cat){
            holder.headContainer.visibility = View.GONE
        }
        else{
            holder.headTitle.text = item.category
            holder.headContainer.visibility = View.VISIBLE
        }
        cat = item.category

        if(item.topping !== null){
            var toppFormated = ""
            item.topping.forEach {
                toppFormated += it
                if(!item.topping.last().equals(it)){
                    toppFormated += "\n"
                }
            }
            holder.topping.text = toppFormated
            holder.topping.visibility = View.VISIBLE
        }
        else{
            holder.topping.visibility = View.GONE
        }

        holder.addButton.setOnClickListener({
            changeAmount(holder,increase = true)
        })

        holder.deleteButton.setOnClickListener({
            changeAmount(holder,increase = false)
        })
    }

   private fun changeAmount(holder: CategoryViewHolder,increase:Boolean = true){
        try {
            var amount = holder.amount.tag as Int
            if(increase){
                amount += 1
            }
            else{
                if(amount > 0){
                    amount -= 1
                }
            }
            holder.amount.text = amount.toString()
            holder.amount.tag = amount
        }
        catch (e:Exception){
            Log.e("CAT",e.message)
        }
    }

    private var items = mutableListOf<MenuObjects>()

    fun setItems(inItems: List<MenuObjects>) {
        this.items.clear()
        this.items.addAll(inItems)
        notifyDataSetChanged()
    }

    private fun returnPrice(item: MenuObjects):String{
        Log.e("price",item.price)
        if(item.price == "0"){
            return "Included"
        }
        return "${item.price}kr"
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CategoryAdapter.CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_item , parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var amount: TextView = itemView.findViewById(R.id.cat_amount)
        var headContainer: ConstraintLayout = itemView.findViewById(R.id.cat_headContainer)
        var headTitle: TextView = itemView.findViewById(R.id.cat_headTitle)
        var name: TextView = itemView.findViewById(R.id.cat_name)
        var price: TextView = itemView.findViewById(R.id.cat_price)
        var topping: TextView = itemView.findViewById(R.id.cat_topping)
        var addButton: ImageView = itemView.findViewById(R.id.cat_addButton)
        var deleteButton: ImageView = itemView.findViewById(R.id.cat_delteButton)
    }
}