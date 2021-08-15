package com.nytimes.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.nytimes.data.entities.Book


@Composable
fun SellerBookList(bookId: String, viewModel: MainViewModel) =
    MaterialTheme {
        viewModel.getBooks(bookId)
        val viewState by viewModel.details.observeAsState()

        Column {
            // Item height will be equal content height
            setToolBar(bookId)

            if (viewState?.results?.isNotEmpty() == true) {
                SetData(viewState?.results)
            }
        }
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SetData(data: List<Book>?) {
    if (!data.isNullOrEmpty()) {
        LazyColumn {
            items(data) {
                if (it.book_details != null && it.book_details?.size ?: 0 > 0) {
                    Text(
                        text = AnnotatedString(it.book_details!![0].title.toString()),
                        modifier = Modifier.absolutePadding(left = 20.dp, top = 20.dp),
                    )
                    GetSubText(it.book_details!![0].description ?: "")
                    GetSubText("Published By: ${it.book_details!![0].publisher}")
                    GetSubText("Price: ${it.book_details!![0].price}")
                    TabRowDefaults.Divider(
                        color = Color.LightGray,
                        modifier = Modifier.absolutePadding(top = 20.dp)
                    )
                }
            }
        }
    }
}

