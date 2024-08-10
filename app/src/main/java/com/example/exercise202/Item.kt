package com.example.exercise202

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val text: String) : Parcelable
