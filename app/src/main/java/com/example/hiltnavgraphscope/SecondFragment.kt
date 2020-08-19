package com.example.hiltnavgraphscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hiltnavgraphscope.di.NavGraphComponentBuilder
import com.example.hiltnavgraphscope.utils.KEY_LABEL
import com.example.hiltnavgraphscope.utils.exampleNavGraphViewModels
import com.example.hiltnavgraphscope.viewmodel.NavGraphViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    @Inject
    lateinit var navGraphComponentBuilder: NavGraphComponentBuilder

    private val viewModel by exampleNavGraphViewModels<NavGraphViewModel>(R.id.nav_graph) {
        navGraphComponentBuilder
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            view.findViewById<TextView>(R.id.textview_second).text =
                "This is second fragment sharing navgraph argument: ${
                    viewModel.savedStateHandle.get<String>(
                        KEY_LABEL
                    )
                }"
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}
