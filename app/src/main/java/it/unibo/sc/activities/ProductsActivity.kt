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
import it.unibo.sc.ProductsAdapter
import it.unibo.sc.databinding.ActivityProductsBinding
import it.unibo.sc.utils.ProductComparator
import it.unibo.sc.viewmodel.ProductsViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest

/***
 * The product list activity.
 */
class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private lateinit var deferredLogout: Deferred<Unit>
    private lateinit var deferredShowProducts: Deferred<Unit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.logoutButton
        button.setOnClickListener {
            deferredLogout = lifecycleScope.async {
                singOutUser()
                startMainActivity()
            }
        }

        val viewModel: ProductsViewModel by viewModels()
        val pagingAdapter = ProductsAdapter(ProductComparator)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = pagingAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        deferredShowProducts = lifecycleScope.async {
            viewModel.products().collectLatest { p ->
                pagingAdapter.submitData(p)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (deferredLogout.isActive) deferredLogout.cancel()
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
