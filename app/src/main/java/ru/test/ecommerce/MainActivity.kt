package ru.test.ecommerce

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import ru.test.ecommerce.databinding.ActivityMainBinding
import ru.test.core.utils.RequestKeys

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var cartBadge: BadgeDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNav()
        binding.navigation.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuCart -> {
                    if (cartBadge.number > 0) navController.navigate(R.id.mainToCart)
                    else Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }
        supportFragmentManager.setFragmentResultListener(
            RequestKeys.ADD_TO_CART.key, this, resultListener
        )
        supportFragmentManager.setFragmentResultListener(
            RequestKeys.OPEN_DETAILS.key, this, resultListener
        )
        supportFragmentManager.setFragmentResultListener(
            RequestKeys.CLEAR_CART.key, this, resultListener
        )
        supportFragmentManager.setFragmentResultListener(
            RequestKeys.BACK_STACK.key, this, resultListener
        )
        cartBadge = binding.navigation.bottomNavigationView.getOrCreateBadge(R.id.menuCart)
        cartBadge.isVisible = false
    }

    private val resultListener = FragmentResultListener { requestKey, _ ->
        when (requestKey) {
            RequestKeys.ADD_TO_CART.key -> {
                cartBadge.number++
                cartBadge.isVisible = true
            }
            RequestKeys.OPEN_DETAILS.key -> navController.navigate(R.id.mainToDetails)
            RequestKeys.CLEAR_CART.key -> {
                cartBadge.number = 0
                cartBadge.isVisible = false
            }
            RequestKeys.BACK_STACK.key -> navController.popBackStack()
        }
    }

    private fun setupNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigation.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> bottomNavVisibility(true)
                else -> bottomNavVisibility(false)
            }
        }
    }

    private fun bottomNavVisibility(isVisible: Boolean) {
        binding.navigation.bottomNavigationView.isVisible = isVisible
    }
}
