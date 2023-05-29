@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetpackcompose.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.jetpackcompose.presentation.BaseApplication
import com.example.jetpackcompose.presentation.components.CircularIndeterminateProgressBar
import com.example.jetpackcompose.presentation.components.FoodCategoryChip
import com.example.jetpackcompose.presentation.components.RecipeCard
import com.example.jetpackcompose.presentation.components.RecipeList
import com.example.jetpackcompose.presentation.components.SearchAppBar
import com.example.jetpackcompose.presentation.components.util.ShimmerCard
import com.example.jetpackcompose.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by viewModels()

    @OptIn(ExperimentalCoroutinesApi::class, ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(darkTheme = application.isDark.value) {
                    val recipes = viewModel.recipes.value

                    val query = viewModel.query.value

                    var selectedCategory = viewModel.selectCategory.value

                    val loading = viewModel.loading.value

                    val page = viewModel.page.value

                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = { viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent) },
                                categories = getAllFoodCategories(),
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onToggleTheme = { application.toggleTheme() }
                            )
                        }
                    ) {
                        RecipeList(
                            loading = loading,
                            recipes = recipes,
                            page = page,
                            onChangeRecipeScrollPosition = viewModel::onChangeRecipeScrollPosition ,
                            nextPageEvent = { viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent)},
                            navController = findNavController(),
                        )
                    }
                }
            }
        }
    }
}

//@Composable
//fun openRecipe() {
//    RecipeList(
//        loading = loading,
//        recipes = recipes,
//        page = page,
//        onChangeRecipeScrollPosition = viewModel::onChangeRecipeScrollPosition ,
//        nextPageEvent = { viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent)},
//        navController = findNavController(),
//    )
//}

//@Composable
//fun SnackbarDemo(
//    onHideSnackbar: () -> Unit
//) {
//    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//        val snackbar = createRef()
//        Snackbar(modifier = Modifier.constrainAs(snackbar)) {
//            bottom.linkTo(parent.bottom)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        },
//        action = {
//            Text(text = "Hide"),
//            modifier = Modifier.clickable(onClick = onHideSnackbar, style = MaterialTheme.typography.bodyMedium)
//        }
//    }
//}

@Composable
fun GradientDemo() {
    val colors = listOf(
        Color.Blue,
        Color.Red,
        Color.Blue
    )

    val brush = linearGradient(
        colors,
        start = Offset(200f, 200f),
        end = Offset(400f, 400f)
    )

    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush)
        )
    }
}