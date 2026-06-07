package com.merabills.question3.constants

import android.os.Build

object UIConfig {

    const val TOTAL_SQUARES = 10_000

    val shouldDisableAnimation: Boolean by lazy{
        // Disable animations for Android 9 and below due to potential performance issues with RecyclerView animations on older devices.
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.P
    }

    // These values are chosen based on device and testing. Values could be change based on devices.

    // Rows rv ui config
    const val ROWS_RV_ITEM_CACHE_SIZE = 5
    const val ROWS_RV_ITEM_PREFETCH_COUNT = 15

    // Squares rv ui config
    const val SQUARES_RV_ITEM_CACHE_SIZE = 5
    // Each row could have 24 view holders, so 100 covers almost 7 rows' view holders helpful inc ase of fast scrolling.
    const val SQUARES_MAX_RECYCLABLE_VIEWS = 100
    const val SQUARES_RV_ITEM_PREFETCH_COUNT = 15

}