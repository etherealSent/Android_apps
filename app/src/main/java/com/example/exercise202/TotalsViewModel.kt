package com.example.exercise202

import androidx.lifecycle.ViewModel

class TotalsViewModel : ViewModel() {
    var total = 0

    fun increaseTotal(): Int {
        total++
        return total
    }
}