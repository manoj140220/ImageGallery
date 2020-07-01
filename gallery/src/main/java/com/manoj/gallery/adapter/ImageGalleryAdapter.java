package com.manoj.gallery.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manoj.gallery.R;
import com.manoj.gallery.adapter.viewholder.ImageGalleryViewHolder;
import com.manoj.gallery.model.ImageListModel;
import com.manoj.gallery.notifier.NotifyImageListClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By : Manoj DB on 29/6/20
 */
public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryViewHolder>{

    private Activity activity;
    private List<ImageListModel> imagePath = new ArrayList<>();
    private NotifyImageListClick notifyImageListClick;

    public ImageGalleryAdapter(Activity activity, List<ImageListModel> imagePath,
                               NotifyImageListClick notifyImageListClick) {
        this.activity = activity;
        this.imagePath = imagePath;
        this.notifyImageListClick = notifyImageListClick;
    }

    @NonNull
    @Override
    public ImageGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageGalleryViewHolder(parent, R.layout.item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGalleryViewHolder holder, final int position) {
        Glide.with(activity)
                .load(imagePath.get(position).getImagePath())
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_broken_image)
                .into(holder.getImageView());
        holder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyImageListClick.NotifyImageClick(imagePath.get(position).getImagePath());
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagePath.size();
    }
}
