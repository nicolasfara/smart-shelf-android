package it.unibo.sc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import it.unibo.sc.databinding.ActivityMainBinding

/**
 * Main activity.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        button.setOnClickListener {
            Log.i("MainActivity", "CIAOO")
            Amplify.Auth.signIn(
                binding.username.text.toString(), binding.password.text.toString(),
                { result ->
                    if (result.isSignInComplete) {
                        Log.i("AuthQuickstart", "Sign in succeeded")
                    } else {
                        Log.i("AuthQuickstart", "Sign in not complete")
                    }
                },
                { Log.e("AuthQuickstart", "Failed to sign in", it) }
            )
        }
    }
}
