package com.example.exercise202

import androidx.test.espresso.ViewAssertion

fun checkRecyclerViewItems(count: Int) : ViewAssertion {
    return RecyclerViewItemCountAssertion(count)
}