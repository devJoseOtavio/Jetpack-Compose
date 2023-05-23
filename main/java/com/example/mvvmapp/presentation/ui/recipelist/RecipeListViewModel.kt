package com.example.jetpackcompose.presentation.ui.recipelist

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.jetpackcompose.domain.model.Recipe
import com.example.jetpackcompose.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val PAGE_SIZE = 30

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String,
): ViewModel(){

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    val selectCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    var categoryScrollPosition: Int = 0

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)

    private var recipeListScrollPosition = 0

    init {
        newSearch(query.value)
    }

    fun newSearch(query: String){
        viewModelScope.launch {

            loading.value = true

            delay(4000)

            val result = repository.search(
                token = token,
                page = 1,
                query = query
            )
            recipes.value = result

            loading.value = false
        }
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectCategory.value = newCategory
        onQueryChanged(category)
    }

    fun nextPage() {
        viewModelScope.launch {
            if ((recipeListScrollPosition + 1 ) >= (page.value * PAGE_SIZE)) {
                loading.value = true
                incrementPage()

                if (page.value > 1) {
                    val result = repository.search(
                        token = token,
                        page = page.value,
                        query = query.value
                    )
                    Log.d(TAG, "nextPage: $(Result)")
                }
            }
        }
    }

    fun onChangeCategoryScrollPosition(position: Int) {
        categoryScrollPosition = position
    }

    fun onChangeRecipeScrollPosition(position: Int) {
        recipeListScrollPosition = position
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangeCategoryScrollPosition(0)
        if (selectCategory.value?.value != query.value)
            clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        selectCategory.value = null
    }

    private fun incrementPage() {
        page.value = page.value +1
    }
}