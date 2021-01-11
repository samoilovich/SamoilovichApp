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
    val decorator = DividerItemDecoration(context, orientation)
    ContextCompat.getDrawable(context, dividerLayoutId)?.also(decorator::setDrawable)
    addItemDecoration(decorator)
}