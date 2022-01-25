package it.unibo.sc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.amplifyframework.auth.AuthException
import com.amplifyframework.kotlin.core.Amplify
import it.unibo.sc.databinding.ActivityProductsBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

/***
 * The product list activity.
 */
class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private lateinit var deferredLogout: Deferred<Unit>

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
    }

    override fun onDestroy() {
        super.onDestroy()
        if (deferredLogout.isActive) deferredLogout.cancel()
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
