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

import static com.manoj.gallery.ImageGallery.IMAGE_DATA_LIST;
import static com.manoj.gallery.ImageGallery.notifySelection;

/**
 * Created By : Manoj DB on 29/6/20
 */
public class ImageGalleryActivity extends AppCompatActivity implements NotifyImageListClick {

    RecyclerView imageGalleryList;
    ImageGalleryAdapter imageGalleryAdapter;
    List<ImageListModel> imageListModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            imageListModels = bundle.getParcelableArrayList(IMAGE_DATA_LIST);

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
        notifySelection.notifySelection(filePath);
        finish();
    }
}