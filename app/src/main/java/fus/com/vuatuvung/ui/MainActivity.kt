package fus.com.vuatuvung.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import fus.com.vuatuvung.R
import fus.com.vuatuvung.databinding.ActivityMainBinding
import fus.com.vuatuvung.ui.base.viewBinding
import fus.com.vuatuvung.utils.Preference

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var navController: NavController
    private var preference: Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFistInstallApp()
        initView()
    }

    private fun initView() {
        binding.homeFab.setOnClickListener {
            navController.navigate(R.id.homeFragment)
        }
        binding.bottomNavigationView.background = null

        binding.bottomNavigationView.menu.getItem(1).isEnabled = false

        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        navController = findNavController(R.id.nav_host_fragment)

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setFistInstallApp() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        preference = Preference.buildInstance(this)
        if (preference?.firstInstall == false) {
            preference?.firstInstall = true
            preference?.setValueCoin(10)
        }
    }
}