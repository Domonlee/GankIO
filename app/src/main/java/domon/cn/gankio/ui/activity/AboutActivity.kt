package domon.cn.gankio.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import domon.cn.gankio.R
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by Domon on 17-1-12.
 */

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        about_back_iv.setOnClickListener { finish() }
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, AboutActivity::class.java)
            context.startActivity(intent)
        }
    }
}
