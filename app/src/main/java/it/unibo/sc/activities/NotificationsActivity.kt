package it.unibo.sc.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import it.unibo.sc.adapters.ProductShelfAdapter
import it.unibo.sc.databinding.ActivityNotificationsBinding
import it.unibo.sc.utils.ProductShelfComparator
import it.unibo.sc.viewmodel.ProductsShelfViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Activity that shows all the expiring products.
 *
 */
class NotificationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: ProductsShelfViewModel by viewModels()
        val listAdapter = ProductShelfAdapter(ProductShelfComparator, this)
        val recyclerView = binding.notificationsRecyclerView

        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.productShelves().collectLatest { prodSh ->
                listAdapter.submitList(prodSh)
            }
        }
    }
}
