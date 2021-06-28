package  com.faraji.mvibase.example.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.faraji.mvibase.example.R
import com.faraji.mvibase.example.databinding.ActivityMainBinding
import com.faraji.mvibase.view.BaseMviActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseMviActivity<ActivityMainBinding>() {

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun initView(savedInstanceState: Bundle?) {
        val navController = findNavController(R.id.nav_host_fragment)
        val topLevelIds = setOf(R.id.nav_image_list)
        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration.Builder(topLevelIds).build()
        )
    }
}
