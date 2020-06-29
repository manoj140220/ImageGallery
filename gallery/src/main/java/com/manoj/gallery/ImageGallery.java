package com.manoj.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.manoj.gallery.background.ImageLoader;
import com.manoj.gallery.model.ImageListModel;
import com.manoj.gallery.notifier.ImageDataNotifier;
import com.manoj.gallery.notifier.NotifyData;
import com.manoj.gallery.notifier.NotifySelection;
import com.manoj.runtime.RuntimePermission;
import com.manoj.runtime.notifier.PermissionNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By : Manoj DB on 29/6/20
 */
public class ImageGallery implements PermissionNotify, NotifyData, NotifySelection {

    private final String[] galleryPermission = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Activity activity;
    private Boolean previewImages = true;
    private ImageDataNotifier imageDataNotifier;
    static final String IMAGE_DATA_LIST = "IMAGE_DATA_LIST";
    static NotifySelection notifySelection;

    public ImageGallery(Activity activity, ImageDataNotifier imageDataNotifier, Boolean previewImages) {
        this.activity = activity;
        this.previewImages = previewImages;
        this.imageDataNotifier = imageDataNotifier;
        notifySelection = this;
        new RuntimePermission(this, galleryPermission, activity);
    }

    @Override
    public void notifyPermissionGrant() {
        loadImages();
    }

    private void loadImages() {
        new ImageLoader(activity, this).execute();
    }

    @Override
    public void notifyPermissionDeny() {
        Toast.makeText(activity, "Please enable the storage permission.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyData(List<ImageListModel> imageListModels) {
        /**
         * Call the activity class to load images from internal or external storage
         * based on boolean status.
         *
         * If boolean status is false return the image path list else display the image.
         * */
        if(previewImages){
            Intent intent = new Intent(activity, ImageGalleryActivity.class);
            intent.putParcelableArrayListExtra(IMAGE_DATA_LIST, (ArrayList<? extends Parcelable>) imageListModels);
            activity.startActivity(intent);
        }else {
            imageDataNotifier.notifyImageListPath(imageListModels);
        }
    }

    @Override
    public void notifySelection(String filePath) {
        imageDataNotifier.notifySelectedImagePath(filePath);
    }
}