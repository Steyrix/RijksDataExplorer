package com.example.rijksdataexplorer.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.rijksdataexplorer.R
import com.example.rijksdataexplorer.core.RijksDataExplorerApplication

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appComponent = (applicationContext as RijksDataExplorerApplication).appComponent
        appComponent.inject(this)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    RijksDataExplorerApp(appComponent = appComponent)
                }
            }
        }
    }
}