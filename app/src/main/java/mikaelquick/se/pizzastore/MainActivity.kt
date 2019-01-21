package mikaelquick.se.pizzastore

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import mikaelquick.se.pizzastore.Fragments.ListFragment
import mikaelquick.se.pizzastore.Fragments.OrdersFragment

class MainActivity : AppCompatActivity() {

    private lateinit var listFragmentFragment: ListFragment
    private lateinit var ordersFragmentFragment: OrdersFragment

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.listContainer,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val shallUpdatee = grantResults[0] ==  PERMISSION_GRANTED
        listFragmentFragment.addResturantsToList(updateLocation = shallUpdatee)
    }

    private fun initFragments(){
        listFragmentFragment = ListFragment()
        ordersFragmentFragment = OrdersFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.listContainer,listFragmentFragment).commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(listFragmentFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                replaceFragment(ordersFragmentFragment)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
