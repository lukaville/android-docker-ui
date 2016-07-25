package ru.lukaville.dockerui.ui.android.core

import android.view.View

interface OnItemClickListener {
    fun onItemClicked(index: Int, view: View)
}