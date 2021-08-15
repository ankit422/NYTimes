package com.nytimes

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.nytimes.ui.MainViewModel
import com.nytimes.ui.SellerBookList
import com.nytimes.ui.SellerList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "welcome") {
                composable("welcome") { SellerList(navController, viewModel) }
                composable("secondScreen") {
                    SecondScreen()
                }
                composable(
                    "bookList/{bookId}",
                    arguments = listOf(navArgument("bookId") { type = NavType.StringType })
                ) {
                    SellerBookList(
                        it.arguments?.getString("bookId") ?: "",
                        viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun SecondScreen() {
    Text(text = "Second screen!")
}
