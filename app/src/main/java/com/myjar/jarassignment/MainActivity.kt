package com.myjar.jarassignment

import android.content.Intent
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myjar.jarassignment.Utils.Resource
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.databinding.ActivityMainBinding
import com.myjar.jarassignment.ui.adapter.ItemAdapter
import com.myjar.jarassignment.ui.vm.JarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var _binding : ActivityMainBinding?= null
    private val binding get() = _binding!!


    private val viewModel by viewModels<JarViewModel>()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        setupUi()
        observeFlows()
    }

    private fun observeFlows() {
        lifecycleScope.launch {
            viewModel.listStringData.collectLatest {result->
                when(result){
                    is Resource.Loading -> Unit
                     is Resource.Failure -> Unit
                    is Resource.Success -> {
                        adapter.submitList(result.result)

                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.navigateToItem.filterNotNull().collectLatest {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("itemId", it)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupUi() {
        val recyclerView: RecyclerView = findViewById(R.id.item_list)
        adapter = ItemAdapter { selectedItem ->
            viewModel.navigateToItemDetail(selectedItem.id)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }


}