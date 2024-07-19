package com.pinheiro.marvelhqs.presenter

import android.app.LauncherActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinheiro.marvelhqs.domain.mapper.comicDTOListTOComicViewObjectList
import com.pinheiro.marvelhqs.domain.usecase.GetCharactersUseCase
import com.pinheiro.marvelhqs.domain.usecase.GetFavoriteUseCase
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import java.lang.Exception

private const val NUMBER_OF_ITEMS = "15"
private const val ITEMS_OFFSET = 15

class MarvelViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
) : ViewModel() {

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _comics = MutableSharedFlow<List<ComicViewObject>>()
    val comics = _comics.asSharedFlow()

    private val _loading = MutableSharedFlow<Boolean>()
    val loading = _loading.asSharedFlow()

    private val _paginLoading = MutableSharedFlow<Boolean>()
    val paginLoading = _paginLoading.asSharedFlow()

    private val _favorites = MutableSharedFlow<List<ComicViewObject>>()
    val favorites = _favorites.asSharedFlow()


    var state by mutableStateOf(ScreenState())
    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
//           try {
            getCharactersUseCase(NUMBER_OF_ITEMS, nextPage.toString())
//                    Result.success(mockList())
//           } catch (e: Exception) {
//               Result.success(arrayListOf())
//           }
        },
        getNextKey = {
            state.page + 16
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        getFavoritesFromDB()
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun getCharacters() {
        viewModelScope.launch {
            paginator.loadNextItems()
//            getCharactersUseCase(NUMBER_OF_ITEMS, ITEMS_OFFSET.toString()).collect {
////                if(page == 2) _loading.emit(it.loading) else _paginLoading.emit(it.loading)
//                if (it.error?.isNotEmpty() == true) {
//                    _error.emit(it.error)
//                    return@collect
//                }
//                it.data?.let { comicsList -> _comics.emit(comicsList) }
//            }
        }
    }

    suspend fun setListPosition(index: Int?) {
        index?.let {
            println("Viewmodel: $index  ${index % 15}")
//            if (index % 15 == 0) getCharactersUseCase(NUMBER_OF_ITEMS, (ITEMS_OFFSET *((index / 15)+1)).toString() ).collect{
//                it.data?.let { comicsList -> _comics.emit(comicsList) }
//            }
        }

    }
    fun getFavoritesFromDB() {
        viewModelScope.launch {
            getFavoriteUseCase().collect { comicsDTO ->
                comicsDTO?.let {
                    _favorites.emit(it.comicDTOListTOComicViewObjectList())
                }
            }
        }
    }

}

fun mockList() = listOf( ComicViewObject(
    id = 1,
    title = "A",
    issueNumber = null,
    variantDescription = null,
    description = "Teste A",
    pageCount = null,
),
    ComicViewObject(
        id = 1,
        title = "A",
        issueNumber = null,
        variantDescription = null,
        description = "Teste A",
        pageCount = null,
    ),
    ComicViewObject(
        id = 2,
        title = "B",
        issueNumber = null,
        variantDescription = null,
        description = "Teste B",
        pageCount = null,
    ),
    ComicViewObject(
        id = 3,
        title = "C",
        issueNumber = null,
        variantDescription = null,
        description = "Teste C",
        pageCount = null,
    ),
)



data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<ComicViewObject> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)

