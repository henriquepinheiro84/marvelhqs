package com.pinheiro.marvelhqs.presentation.ui.comic

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pinheiro.marvelhqs.R
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import org.koin.androidx.compose.koinViewModel


@Composable
fun ComicItem(
    comic: ComicViewObject?,
    modifier: Modifier = Modifier,
    isFavoriteSelected: Boolean = false
) {
    val viewModel = koinViewModel<ComicItemViewModel>()
    var checked by remember {
        mutableStateOf(isFavoriteSelected)
    }
    Card(
        modifier = modifier,
        elevation =  CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(horizontal = 16.dp)
        ) {
            println("Tumbnail: ${comic?.thumbnail}")
            AsyncImage(
                model = comic?.thumbnail,
                contentDescription = comic?.description,
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = comic?.title ?: stringResource(R.string.NO_TITLE),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.NUMBER_OF_PAGES_TITLE)
                        .replace("%d",
                            comic?.pageCount?.toString() ?: stringResource(id = R.string.NUMBER_OF_PAGES_NOT_INFORMED)
                        ),
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = comic?.description ?: "No description",
//                    modifier = Modifier.fillMaxWidth()
//                )
            }
            IconToggleButton(checked = checked,
                modifier = Modifier
                    .weight(1f),
                onCheckedChange = {
                    if (it)
                        comic?.let{ viewModel.saveFavorite(comic)}
                    else
                        viewModel.deleteFavorite(comic)
                    checked = it
                }) {
                if (checked) {

                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Favorite Button unselected",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                } else {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite Button selected",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ComicItemPreview() {
//    ComposePaging3CachingTheme {
        ComicItem(
            comic = ComicViewObject(
                id = 1,
               title = "Beer",
               issueNumber = 3.0,
               variantDescription = "07/2023",
               description = "This is a description for a beer. \nThis is the next line.",
               pageCount = 1,
                thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg"
            ),
            modifier = Modifier.fillMaxWidth(),
            isFavoriteSelected = true
        )
    }
//}