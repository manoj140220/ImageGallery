package com.manoj.gallery.adapter.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.manoj.gallery.R;

/**
 * Created By : Manoj DB on 29/6/20
 */
public class ImageGalleryViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public ImageGalleryViewHolder(ViewGroup parent, int layout) {
        super(LayoutInflater.from(parent.getContext()).inflate(layout,
                parent, false));
        imageView = itemView.findViewById(R.id.image_view);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
