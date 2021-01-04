package com.samoilovich.courseapp.ext

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addHorizontalDivider(dividerLayoutId: Int) {
    addDivider(dividerLayoutId, DividerItemDecoration.HORIZONTAL)
}

fun RecyclerView.addVerticalDivider(dividerLayoutId: Int) {
    addDivider(dividerLayoutId, DividerItemDecoration.VERTICAL)
}

fun RecyclerView.addDivider(dividerLayoutId: Int, orientation: Int) {
    val drawable = ContextCompat.getDrawable(context, dividerLayoutId)
    val decorator = DividerItemDecoration(context, orientation)
    drawable?.let {
        decorator.setDrawable(drawable)
    }
    addItemDecoration(decorator)
}