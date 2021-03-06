package it.unibo.sc.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.core.Amplify.AlreadyConfiguredException
import com.amplifyframework.kotlin.core.Amplify
import it.unibo.sc.databinding.ActivityMainBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * The login activity.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var deferredSession: Deferred<Unit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addAmplifyPlugins()

        val button = binding.loginButton
        deferredSession = lifecycleScope.async {
            if (isUserAuthenticated()) {
                Log.i("MainActivity", "User already logged in")
                startProductsActivity()
            }
        }
        button.setOnClickListener { loginProcess() }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (deferredSession.isActive) deferredSession.cancel()
    }

    private suspend fun signInUser(username: String, password: String): AuthSignInResult? {
        return try {
            Amplify.Auth.signIn(username, password)
        } catch (error: AuthException) {
            Log.e("Login", "Sign in failed", error)
            null
        }
    }

    private suspend fun isUserAuthenticated(): Boolean {
        return try {
            val session = Amplify.Auth.fetchAuthSession()
            Log.i("CheckLoginSession", "Auth session = $session")
            session.isSignedIn
        } catch (error: AuthException) {
            Log.e("CheckLoginSession", "Failed to fetch auth session", error)
            false
        }
    }

    private fun loginProcess() {
        lifecycleScope.launch {
            signInUser(binding.username.text.toString(), binding.password.text.toString())?.let {
                if (it.isSignInComplete) {
                    Log.i("LoginProcess", "Authentication complete")
                    startProductsActivity()
                } else {
                    Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_LONG).show()
                }
            } ?: Toast.makeText(applicationContext, "Error on Login process", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun addAmplifyPlugins() {
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.configure(applicationContext)

            Log.i("AmplifyConfiguration", "Initialized Amplify")
        } catch (error: AlreadyConfiguredException) {
            Log.e("AmplifyConfiguration", "Amplify is already configured")
        }
    }

    private fun startProductsActivity() {
        startActivity(Intent(this, ProductsActivity::class.java))
    }
}
