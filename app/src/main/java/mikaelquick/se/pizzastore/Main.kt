package mikaelquick.se.pizzastore

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity() {

    private lateinit var listFragment: List
    private lateinit var ordersFragment: Orders

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.listContainer,fragment)
        transaction.commit();
    }

    private fun initFragments(){
        listFragment = List()
        ordersFragment = Orders()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(listFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                replaceFragment(ordersFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        initFragments()
        transaction.add(R.id.listContainer,listFragment)

        transaction.commit();


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
