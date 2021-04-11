package com.ragvax.dictionary.utils

import android.content.Context
import android.view.View

fun View.show() { visibility = View.VISIBLE}

fun View.hide() { visibility = View.GONE }

fun Context.hideViews(vararg views: View) = views.forEach { it.visibility = View.GONE }

fun Context.showViews(vararg views: View) = views.forEach { it.visibility = View.VISIBLE }