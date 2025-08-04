package com.example.invest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.invest.domain.model.Screen
import com.example.invest.presentation.screens.HomeScreen
import com.example.invest.presentation.screens.OnboardingScreen
import com.example.invest.ui.theme.AppColors
import com.example.invest.ui.theme.InvestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InvestTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = AppColors.default.primary
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Onboarding.name) {
                        composable(Screen.Onboarding.name) {
                            OnboardingScreen(
                                navigator = navController,
                                innerPadding = innerPadding
                            )
                        }

                        composable(Screen.Home.name) {
                            HomeScreen(navigator = navController, innerPadding = innerPadding)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InvestTheme {
        Greeting("Android")
    }
}