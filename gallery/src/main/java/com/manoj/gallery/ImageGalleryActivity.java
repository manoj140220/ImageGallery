package com.manoj.gallery;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manoj.gallery.adapter.ImageGalleryAdapter;
import com.manoj.gallery.model.ImageListModel;
import com.manoj.gallery.notifier.NotifyImageListClick;
import com.manoj.gallery.view.GridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By : Manoj DB on 29/6/20
 */
@SuppressWarnings("all")
public class ImageGalleryActivity extends AppCompatActivity implements NotifyImageListClick {

    private RecyclerView imageGalleryList;
    private ImageGalleryAdapter imageGalleryAdapter;
    private List<ImageListModel> imageListModels = new ArrayList<>();
    private final String image_data_list = "IMAGE_DATA_LIST";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            imageListModels = bundle.getParcelableArrayList(image_data_list);

        if(imageListModels != null && imageListModels.isEmpty()){
            Toast.makeText(this, "No Data Found To Display", Toast.LENGTH_SHORT).show();
            finish();
        }

        imageGalleryList = findViewById(R.id.image_gallery_list);
        imageGalleryList.setLayoutManager(new GridLayoutManager(this, 4));
        int gridSpacing = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        imageGalleryList.addItemDecoration(new GridItemDecoration(gridSpacing));
        imageGalleryAdapter = new ImageGalleryAdapter(this, imageListModels, this);
        imageGalleryList.setAdapter(imageGalleryAdapter);
    }

    @Override
    public void NotifyImageClick(String filePath) {
        ImageGallery imageGallery = ImageGallery.getInstance();
        imageGallery.getNotifySelection().notifySelection(filePath);
        finish();
    }
}