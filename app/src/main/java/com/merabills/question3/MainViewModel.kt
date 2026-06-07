package com.merabills.question3

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merabills.question3.constants.UIConfig
import com.merabills.question3.data.DataRepository
import com.merabills.question3.domain.RemoveSquareUseCase
import com.merabills.question3.models.SquareItemData
import com.merabills.question3.models.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // TODO: Inject
    private val dataRepository: DataRepository = DataRepository()
    private val removeSquareUseCase: RemoveSquareUseCase = RemoveSquareUseCase()

    // In this case, it is unlikely to have a large number of deleted ids. So, SavedStateHandle can handle this.
    private val deletedIds =
        savedStateHandle.get<Set<Int>>(KEY_DELETED_IDS)?.toMutableSet() ?: mutableSetOf()

    // TODO: Consider savedStateHandle at least for deleted ids to preserve state after process killed.
    private val _uiState = MutableStateFlow<UIState>(
        UIState(
            isLoading = true, listOf()
        )
    )

    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        Log.d(TAG, "init: deletedIds = $deletedIds")
        initUIState()
    }

    private fun initUIState() {
        Log.d(TAG, "initUIState: deletedIds = $deletedIds")
        viewModelScope.launch(Dispatchers.Default) {
            val rowsData = dataRepository.generateRows(UIConfig.TOTAL_SQUARES, deletedIds)
            _uiState.update {
                it.copy(rowsData = rowsData, isLoading = false)
            }
        }
    }

    fun removeSquare(
        squareItemData: SquareItemData
    ) {
        Log.d(TAG, "removeSquare: squareItemData = ${squareItemData.index}")
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update { currentState ->
                val rowsData = currentState.rowsData
                val updatedRows = removeSquareUseCase(rowsData, squareItemData)
                currentState.copy(rowsData = updatedRows)
            }

            deletedIds.add(squareItemData.index.toInt())
            savedStateHandle[KEY_DELETED_IDS] = deletedIds
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val KEY_DELETED_IDS = "deleted_ids"
    }


}