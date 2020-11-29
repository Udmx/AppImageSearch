package ir.udmx.imagesearchapp.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.udmx.imagesearchapp.R
import com.udmx.imagesearchapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment :Fragment(R.layout.fragment_gallery){
    private val viewModel by viewModels<GalleryViewModel>()

    private var _binding :FragmentGalleryBinding?=null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)


        val adapter = UnSplashPhotoAdapter()
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView .adapter = adapter
        }


        viewModel.photos.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }
}