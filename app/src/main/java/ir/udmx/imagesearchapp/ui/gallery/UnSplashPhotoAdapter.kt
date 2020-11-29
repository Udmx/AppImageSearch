package ir.udmx.imagesearchapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.udmx.imagesearchapp.R
import com.udmx.imagesearchapp.databinding.ItemUnsplashPhotoBinding
import ir.udmx.imagesearchapp.data.UnSplashPhoto

class UnSplashPhotoAdapter :
    PagingDataAdapter<UnSplashPhoto, UnSplashPhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    class PhotoViewHolder(private val binding: ItemUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: UnSplashPhoto) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
                textViewUserName.text = photo.user.username
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<UnSplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnSplashPhoto, newItem: UnSplashPhoto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UnSplashPhoto,
                newItem: UnSplashPhoto
            ) = oldItem == newItem
        }
    }
}