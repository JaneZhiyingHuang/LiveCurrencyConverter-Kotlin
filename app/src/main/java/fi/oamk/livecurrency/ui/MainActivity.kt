package fi.oamk.livecurrency.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fi.oamk.livecurrency.ui.theme.LiveCurrencyTheme
import fi.oamk.livecurrency.viewmodel.CurrencyViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveCurrencyTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "currencyScreen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("currencyScreen") {
                            CurrencyScreen(viewModel, navController)
                        }
                        composable("infoScreen") {
                            InfoScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
