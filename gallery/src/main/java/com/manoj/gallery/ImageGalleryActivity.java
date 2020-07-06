package com.manoj.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manoj.gallery.adapter.ImageGalleryAdapter;
import com.manoj.gallery.library.CreateFile;
import com.manoj.gallery.model.ImageListModel;
import com.manoj.gallery.notifier.NotifyImageListClick;
import com.manoj.gallery.view.GridItemDecoration;

import java.io.File;
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
    private final String is_camera_action = "IS_CAMERA_ACTION";
    private boolean isCameraAction;
    private static final int CAMERA_REQUEST = 100;
    private ImageGallery imageGallery;
    private String imageFilePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);
        imageGallery  = ImageGallery.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            imageListModels = bundle.getParcelableArrayList(image_data_list);
            isCameraAction = bundle.getBoolean(is_camera_action);
        }

        if(!isCameraAction){
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
        }else {
            CreateFile createFile = new CreateFile();
            File file = new File(createFile.getFileDirectory(), createFile.getImageName());
            imageFilePath = file.getAbsolutePath();
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, createFile.getImageUri(file, this));
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    @Override
    public void NotifyImageClick(String filePath) {
        imageGallery.getNotifySelection().notifySelection(filePath);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            imageGallery.getNotifySelection().notifySelection(imageFilePath);
            finish();
        }
    }
}