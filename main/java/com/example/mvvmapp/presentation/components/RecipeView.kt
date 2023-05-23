package com.example.jetpackcompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.domain.model.Recipe
import com.example.jetpackcompose.util.DEFAULT_RECIPE_IMAGE
import com.example.jetpackcompose.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RecipeView(
    recipe: Recipe,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        recipe.featuredImage?.let { url ->
            val image = loadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    "imagem",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                recipe.title?.let { title ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ){
                        Text(
                            text = title,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start)
                            ,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        val rank = recipe.rating.toString()
                        Text(
                            text = rank,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .align(Alignment.CenterVertically)
                            ,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                recipe.publisher?.let { publisher ->
                    val updated = recipe.dateUpdated
                    Text(
                        text = if(updated != null) {
                            "Updated $updated by $publisher"
                        }
                        else {
                            "By $publisher"
                        }
                        ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                        ,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                recipe.description?.let { description ->
                    if(description != "N/A"){
                        Text(
                            text = description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                            ,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                for(ingredient in recipe.ingredients){
                    Text(
                        text = ingredient,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                        ,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                recipe.cookingInstructions?.let { instructions ->
                    Text(
                        text = instructions,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                        ,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}