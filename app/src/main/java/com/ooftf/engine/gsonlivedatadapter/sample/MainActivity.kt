package com.ooftf.engine.gsonlivedatadapter.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.ooftf.engine.glda.adapter.LiveDataTypeAdapterFactory

class MainActivity : AppCompatActivity() {
    lateinit var jsonString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GsonBuilder().apply {
            registerTypeAdapterFactory(LiveDataTypeAdapterFactory())
        }.create().apply {
            toJson(TestBean()).apply {
                jsonString = this
                Log.e("MainActivity", this)
            }

            fromJson(jsonString, TestBean::class.java).apply {
                Log.e("MainActivityResult", toJson(this))

            }
        }


    }
}