package com.nytimes.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nytimes.data.entities.Seller
import com.nytimes.utils.DateTypeConverter
import com.nytimes.utils.Resource


@Composable
fun SellerList(navController: NavController, viewModel: MainViewModel) = MaterialTheme {
    val viewState by viewModel.allSellers.observeAsState()

    Column {
        // Item height will be equal content height
        setToolBar("Best Sellers")
        if (viewState?.status == Resource.Status.SUCCESS) {
            SetData(viewState?.data, navController, viewModel)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SetData(data: List<Seller>?, navController: NavController, viewModel: MainViewModel) {
    if (!data.isNullOrEmpty()) {
        val map = HashMap<String, MutableList<Seller>>()
        for (item in data) {
            if (map.containsKey(item.updated))
                map[item.updated]?.add(item)
            else
                map[item.updated.toString()] = mutableListOf(item)
        }

        LazyColumn {
            map.forEach { (initial, contactsForInitial) ->
                stickyHeader {
                    SetHeader(initial)
                }

                items(contactsForInitial) {
                    ClickableText(
                        text = AnnotatedString(it.display_name.toString()),
                        modifier = Modifier.absolutePadding(left = 20.dp, top = 20.dp),
                        onClick = { _ ->
                            navController.navigate("bookList/${it.list_name}")
                        }
                    )
                    GetSubText("Published On: ${DateTypeConverter().toString(it.newest_published_date)}  (${it.updated})")
                    TabRowDefaults.Divider(
                        color = Color.LightGray,
                        modifier = Modifier.absolutePadding(
                            top = 20.dp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun SetHeader(initial: String) {
    Text(
        text = initial,
        fontSize = 22.sp,
        modifier = Modifier
            .background(Color.Black)
            .absolutePadding(left = 20.dp, top = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        color = Color.White,
    )
}

@Composable
fun GetSubText(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = Modifier.absolutePadding(
            left = 20.dp,
            top = 5.dp,
        )
    )
}


@Composable
fun setToolBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.ArrowBack, "")
            }
        },
    )
}


