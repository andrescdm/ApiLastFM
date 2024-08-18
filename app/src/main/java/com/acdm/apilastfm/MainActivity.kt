package com.acdm.apilastfm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.acdm.apilastfm.presentation.ui.ContentPrincipalView
import com.acdm.apilastfm.presentation.ui.ContentSecondaryScreen
import com.acdm.apilastfm.presentation.ui.theme.ApiLastFMTheme
import com.acdm.apilastfm.presentation.viewmodel.ApiViewModel
import com.acdm.apilastfm.presentation.viewmodel.ApiViewModelSongs
import com.example.pruebatecnicabolsiyo.core.model.Routes
import com.example.pruebatecnicabolsiyo.domain.Constans
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val apiViewModel: ApiViewModel by viewModels()
    private val apiViewModelSongs: ApiViewModelSongs by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiLastFMTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Screen1.routes) {
                    composable(Routes.Screen1.routes) {
                        ContentPrincipalView(apiViewModel, navController)
                    }
                    composable(
                        Routes.Screen2.routes,
                        arguments = listOf(navArgument(Constans.ID) { type = NavType.StringType })
                    ) { backStackEntry ->
                        ContentSecondaryScreen(
                            apiViewModelSongs,
                            id = backStackEntry.arguments?.getString(Constans.ID) ?: ""
                        )
                    }
                }
            }
        }
    }
}
