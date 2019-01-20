package mikaelquick.se.pizzastore.Views

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import mikaelquick.se.pizzastore.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dialog = Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_detail)
        getSupportActionBar()?.hide();
    }
}
