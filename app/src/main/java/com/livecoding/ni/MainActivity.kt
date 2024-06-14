package com.livecoding.ni

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.livecoding.ni.databinding.ActivityMainBinding


const val THIRD_PARTY_APP_MESSAGE_KEY = "thirdPartyAppMessageKey"

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val vm by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        initObservers()
        initActions()

    }

    private fun initObservers() {
        vm.stateLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun MainActivity.initActions() {
        binding.btnSend.setOnClickListener {

            val text = binding.etSendit.text.toString()


            startActivityForResult(Intent().apply {
                this.setClassName(
                    "com.livecoding.status_app",
                    "com.livecoding.status_app.MainActivity"
                )

                putExtra(THIRD_PARTY_APP_MESSAGE_KEY, text)
            }, 200)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            vm.setState(resultCode == RESULT_OK)
        }

    }

}