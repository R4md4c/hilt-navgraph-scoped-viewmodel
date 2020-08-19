package com.example.hiltnavgraphscope

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hiltnavgraphscope.di.NavGraphComponentBuilder
import com.example.hiltnavgraphscope.utils.KEY_LABEL
import com.example.hiltnavgraphscope.utils.exampleNavGraphViewModels
import com.example.hiltnavgraphscope.viewmodel.NavGraphViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navGraphComponentBuilder: NavGraphComponentBuilder

    private val viewModel by exampleNavGraphViewModels<NavGraphViewModel>(R.id.nav_graph) {
        navGraphComponentBuilder
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Create the navigation graph programmatically to set the startDestinations arguments.
        if (savedInstanceState == null) {
            val navHost = NavHostFragment.create(R.navigation.nav_graph, Bundle().apply {
                putString(KEY_LABEL, "This is coming from the R.navigation.nav_graph")
            })
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, navHost)
                .commitNow()
        }

        lifecycleScope.launchWhenStarted {
            println("SavedStateHandle MainActivity: ${viewModel.savedStateHandle.get<String>(KEY_LABEL)}")
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
