package com.floracatalana.floracatalana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.floracatalana.floracatalana.presentation.navigation.RootNavGraph
import com.floracatalana.floracatalana.presentation.navigation.Screen
import com.floracatalana.floracatalana.ui.theme.FloraCatalanaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloraCatalanaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavGraph(
                        navController = rememberNavController(),
                        startDestination = Screen.Search.route
                    )
                }
            }
        }
    }
}