package it.unibo.sc.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amplifyframework.auth.AuthException
import com.amplifyframework.kotlin.core.Amplify
import it.unibo.sc.ProductsWarehouseAdapter
import it.unibo.sc.databinding.ActivityProductsBinding
import it.unibo.sc.utils.ProductWarehouseComparator
import it.unibo.sc.viewmodel.ProductsWarehouseViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/***
 * The ProductWarehouse list Activity.
 */
class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private lateinit var deferredShowProducts: Deferred<Unit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.logoutButton
        button.setOnClickListener {
            lifecycleScope.launch {
                singOutUser()
                startMainActivity()
            }
        }

        val viewModel: ProductsWarehouseViewModel by viewModels()
        val pagingAdapter = ProductsWarehouseAdapter(ProductWarehouseComparator, this)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = pagingAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        deferredShowProducts = lifecycleScope.async {
            viewModel.productsWarehouse().collectLatest { p ->
                pagingAdapter.submitData(p)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (deferredShowProducts.isActive) deferredShowProducts.cancel()
    }

    private suspend fun singOutUser(): Int {
        return try {
            Amplify.Auth.signOut()
            Log.i("CheckLogout", "Signed out successfully")
        } catch (error: AuthException) {
            Log.e("CheckLogout", "Sign out failed", error)
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
