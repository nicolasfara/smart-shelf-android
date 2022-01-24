package it.unibo.sc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.kotlin.core.Amplify
import it.unibo.sc.databinding.ActivityMainBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

/**
 * Main activity.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var deferredSession: Deferred<Unit>
    private lateinit var deferredLogin: Deferred<Unit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = binding.loginButton

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("MainActivity", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MainActivity", "Could not inizialize Amplify", error)
        }

        val intent = Intent(this, ItemsActivity::class.java)

        deferredSession = lifecycleScope.async {
            if (isUserAuthenticated()) {
                Log.i("MainActivity", "User already logged in")
                startActivity(intent)
            }
        }

        button.setOnClickListener {
            deferredLogin = lifecycleScope.async {
                signInUser(binding.username.text.toString(), binding.password.text.toString())?.let {
                    if (it.isSignInComplete) {
                        Log.i("LoginProcess", "Authentication complete")
                    } else {
                        Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_LONG).show()
                    }
                } ?: Toast.makeText(applicationContext, "Error on Login process", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (deferredLogin.isActive) deferredLogin.cancel()
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
}
