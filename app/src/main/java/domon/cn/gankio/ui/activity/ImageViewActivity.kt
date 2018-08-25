package domon.cn.gankio.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import domon.cn.gankio.R
import kotlinx.android.synthetic.main.activity_imageview.*

/**
 * Created by Domon on 16-8-19.
 */
class ImageViewActivity : AppCompatActivity() {
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imageview)

        url = intent.getStringExtra("url")
        myIv.setOnClickListener { finish() }

        Glide.with(this).load(url).into(myIv)
    }

    // I dont know what is **companion object**
    // answer: kotlin use **companion object** to define static function
    // use **object** to define static class
    companion object {

        fun startActivity(context: Context, url: String) {
            val intent = Intent(context, ImageViewActivity::class.java)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }
}
