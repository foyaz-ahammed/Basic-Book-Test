package com.kou.example

import com.kou.example.adapters.BookListAdapter
import com.kou.example.entities.Response
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Functional tests for
 */
class BookListAdapterTest {
    @Test
    fun checkDiffCallback() {
        val item1 = Response.Book("base url", "May 8", "May 9", "base item", "link", "type1", true)
        val item2 = Response.Book("base url", "May 8", "May 9", "base item", "link", "type2", false)
        val item3 = Response.Book("base url", "May 8", "May 9", "other item", "link", "type3", true)
        val item4 = Response.Book("other url", "May 8", "May 9", "other item", "link", "type4", true)

        assertEquals("Check1", BookListAdapter.DiffCallback.areItemsTheSame(item1, item2), true)
        assertEquals("Check2", BookListAdapter.DiffCallback.areContentsTheSame(item1, item2), true)
        assertEquals("Check3", BookListAdapter.DiffCallback.areItemsTheSame(item1, item3), true)
        assertEquals("Check4", BookListAdapter.DiffCallback.areContentsTheSame(item1, item3), false)
        assertEquals("Check5", BookListAdapter.DiffCallback.areItemsTheSame(item1, item4), false)
    }
}