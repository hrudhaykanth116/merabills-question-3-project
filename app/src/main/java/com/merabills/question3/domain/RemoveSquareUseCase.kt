package com.merabills.question3.domain

import com.merabills.question3.models.RowItemData
import com.merabills.question3.models.SquareItemData
import com.merabills.question3.models.UIState
import kotlin.collections.mapNotNull

/**
 * Use case to remove a square from the list of rows.
 * It takes the current list of rows and the square to be removed as input,
 * and returns an updated list of rows with the specified square removed.
 */
class RemoveSquareUseCase {

    operator fun invoke(
        rowsData: List<RowItemData>,
        squareItemData: SquareItemData,
    ): List<RowItemData> {
        val updatedRows = rowsData.mapNotNull { row ->

            // If not the row containing the square to be removed, return the row as is.
            if (row.rowIndex != squareItemData.rowIndex) return@mapNotNull row

            val updatedSquares = row.squaresList.filterNot {
                it.index == squareItemData.index
            }

            if (updatedSquares.isEmpty())
                null
            else
                row.copy(squaresList = updatedSquares)

        }
        return updatedRows
    }

}