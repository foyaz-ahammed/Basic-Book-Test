package com.kou.example.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.kou.example.R
import com.kou.example.adapters.BookListAdapter
import com.kou.example.databinding.ActivityMainBinding
import com.kou.example.entities.LoadResult
import com.kou.example.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Activity for main screen
 */
class MainActivity : AppCompatActivity() {
    // View model
    private val viewModel by viewModel<MainViewModel>()

    // Binding variable
    lateinit var binding: ActivityMainBinding

    // Adapter
    private val adapter = BookListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate binding and set content view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.btnRetry.setOnClickListener {
            viewModel.fetchData()
        }

        adapter.setItemClickListener {
            val toast = Toast.makeText(
                this,
                "You will be redirected to \"" + it.url + "\" soon",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }

        // Observe livedata of view model
        viewModel.bookList.observe(this) {
            adapter.submitList(it)
        }
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it == LoadResult.LOADING
            binding.recyclerView.isVisible = it == LoadResult.SUCCESS
            binding.errorViews.isVisible = it == LoadResult.FAILURE
        }

        // Fetch data once when the activity is first created
        if (savedInstanceState == null) {
            viewModel.fetchData()
        }
    }
}