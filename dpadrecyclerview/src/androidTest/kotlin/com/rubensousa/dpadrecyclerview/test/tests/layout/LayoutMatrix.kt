/*
 * Copyright 2022 Rúben Sousa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rubensousa.dpadrecyclerview.test.tests.layout

import android.graphics.Rect
import androidx.collection.CircularArray

abstract class LayoutMatrix(val width: Int, val height: Int) {

    companion object {
        val EMPTY_INSETS = Rect()
    }

    private val circularArray = CircularArray<ViewItem>()

    fun scrollHorizontallyBy(offset: Int) {
        circularArray.forEach { view ->
            view.offsetHorizontally(offset)
        }
    }

    fun scrollVerticallyBy(offset: Int) {
        circularArray.forEach { view ->
            view.offsetVertically(offset)
        }
    }

    fun getFirstView(): ViewItem? {
        if (circularArray.isEmpty) {
            return null
        }
        return circularArray.first
    }

    fun getLastView(): ViewItem? {
        if (circularArray.isEmpty) {
            return null
        }
        return circularArray.last
    }

    fun getViewAt(index: Int): ViewItem {
        return circularArray.get(index)
    }

    fun getNumberOfViewsInLayout(): Int {
        return circularArray.size()
    }

    fun getViewsInLayout(): List<ViewItem> {
        return List(circularArray.size()) { index -> circularArray.get(index) }
    }

    protected fun append(item: ViewItem) {
        circularArray.addLast(item)
    }

    protected fun prepend(item: ViewItem) {
        circularArray.addFirst(item)
    }

    private inline fun CircularArray<ViewItem>.forEach(action: (item: ViewItem) -> Unit) {
        for (i in 0 until size()) {
            action(get(i))
        }
    }

}
