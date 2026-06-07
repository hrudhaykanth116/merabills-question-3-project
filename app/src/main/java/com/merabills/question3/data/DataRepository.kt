package com.merabills.question3.data

import com.merabills.question3.models.RowItemData
import com.merabills.question3.models.SquareItemData
import com.merabills.question3.utils.RandomColorGenerator
import kotlin.random.Random

class DataRepository {

    // TODO: inject this
    private val randomColorGenerator = RandomColorGenerator()

    /**
     * Generates list of rows, each row contains random number of squares between 1 and 100.
     * Each square has unique index and random color.
     */
    fun generateRows(noOfSquares: Int, excludedIds: Set<Int>): List<RowItemData> {

        val rows = mutableListOf<RowItemData>()

        var squareIndex = 0
        var rowIndex = 0

        while (squareIndex < noOfSquares) {

            val pair = generateSquaresDataListForRow(squareIndex, noOfSquares, excludedIds, rowIndex)

            squareIndex = pair.second

            pair.first?.let { rowItemData ->
                rows.add(rowItemData)
                rowIndex++
            }
        }

        return rows

    }

    private fun generateSquaresDataListForRow(
        initialSquareIndex: Int,
        maxSquares: Int,
        excludedIds: Set<Int>,
        rowIndex: Int
    ): Pair<RowItemData?, Int> {
        var currentSquareIndex = initialSquareIndex
        val squareItemDataList = mutableListOf<SquareItemData>()

        repeat(Random.nextInt(1, 101)) {

            // Created all required squares, break the loop.
            if (currentSquareIndex >= maxSquares) return@repeat

            // Do not add deleted square to the list
            if (currentSquareIndex !in excludedIds) {
                // Consider this square only if not is not excluded.
                squareItemDataList.add(
                    SquareItemData(
                        index = currentSquareIndex.toString(),
                        rowIndex = rowIndex,
                        color = randomColorGenerator.getRandomColor()
                    )
                )
            }
            currentSquareIndex++

        }

        val rowItemData = if(squareItemDataList.isEmpty()){
            null
        }else{
            RowItemData(
                rowIndex = rowIndex,
                squaresList = squareItemDataList
            )
        }

        return Pair(rowItemData, currentSquareIndex)
    }


}