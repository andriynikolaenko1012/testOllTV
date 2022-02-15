package com.test.testoll.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.test.testoll.R
import com.test.testoll.databinding.ActivityDetailBinding
import com.test.testoll.ext.load

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            image.load(intent.getStringExtra("channel_icon").toString())
            name.text = intent.getStringExtra("program_name").toString()
            toolbar.title = intent.getStringExtra("channel_name").toString()
            description.text = intent.getStringExtra("program_description").toString()

            toolbar.setNavigationOnClickListener { onBackPressed() }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top)
    }
}