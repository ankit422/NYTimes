package com.nytimes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.movielist.utils.autoCleared
import com.nytimes.data.entities.Seller
import com.nytimes.databinding.DetailFragmentBinding
import com.nytimes.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var binding: DetailFragmentBinding by autoCleared()
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.start(arguments?.getInt("id"))
    }


    private fun setupObservers() {
        viewModel.allSellers.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data?.isNullOrEmpty() != true) {
                        setData(it)
                    }
                }

                Resource.Status.ERROR -> {
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                }
            }
        })
    }

    private fun setData(dataItem: Resource<List<Seller>>?) {
        dataItem?.data.let {
            binding.title.text = it?.get(0)?.display_name
            binding.releaseDate.text = "Released On: ${it?.get(0)?.newest_published_date}"
            binding.description.text = "Released On: ${it?.get(0)?.update}"
        }
    }
}
