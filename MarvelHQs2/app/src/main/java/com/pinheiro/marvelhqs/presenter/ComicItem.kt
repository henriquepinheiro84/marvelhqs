package com.pinheiro.marvelhqs.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject


@Composable
fun ComicItem(
    comic: ComicViewObject?,
    modifier: Modifier = Modifier
) {
    var imagePath = ""
    comic?.thumbnail?.let {
        imagePath = it.path ?: ""
    }
    Card(
        modifier = modifier,
        elevation =  CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            AsyncImage(
                model = imagePath ,
                contentDescription = comic?.title,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = comic?.title ?: "No title",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = comic?.variantDescription ?: "No description",
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = comic?.description ?: "No description",
                    modifier = Modifier.fillMaxWidth()
                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = "First brewed in ${comic?.variantDescription}",
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.End,
//                    fontSize = 8.sp
//                )
            }
        }
    }
}

@Preview
@Composable
fun BeerItemPreview() {
//    ComposePaging3CachingTheme {
        ComicItem(
            comic = ComicViewObject(
                id = 1,
               title = "Beer",
               issueNumber = 3.0,
               variantDescription = "07/2023",
               description = "This is a description for a beer. \nThis is the next line.",
               pageCount = 1,
                images = null,
                thumbnail = null
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
//}