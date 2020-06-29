package com.manoj.imagegallery;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.manoj.gallery.ImageGallery;
import com.manoj.gallery.model.ImageListModel;
import com.manoj.gallery.notifier.ImageDataNotifier;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageDataNotifier {

    ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedImage = findViewById(R.id.selected_image);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.load_images_gallery:
                new ImageGallery(
                        MainActivity.this,
                        MainActivity.this,
                        true);
                break;
            case R.id.load_gallery_images_path:
                new ImageGallery(
                        MainActivity.this,
                        MainActivity.this,
                        false);
                break;
        }
    }

    @Override
    public void notifyImageListPath(List<ImageListModel> imageList) {
        Log.e("TAG_DATA", new Gson().toJson(imageList));
    }

    @Override
    public void notifySelectedImagePath(String filePath) {
        Glide.with(this)
            .load(filePath)
            .into(selectedImage);
    }
}