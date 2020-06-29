package com.manoj.gallery.background;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.manoj.gallery.model.ImageListModel;
import com.manoj.gallery.notifier.NotifyData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By : Manoj DB on 29/6/20
 */
public class ImageLoader extends AsyncTask<Void, Void, List<ImageListModel>> {

    private Activity activity;
    private NotifyData notifyData;
    private List<ImageListModel> imageListModels = new ArrayList<>();
    private final String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";

    private String[] projection = {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.HEIGHT,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.SIZE
    };

    public ImageLoader(Activity activity, NotifyData notifyData) {
        this.activity = activity;
        this.notifyData = notifyData;
    }

    @Override
    protected List<ImageListModel> doInBackground(Void... voids) {
        Boolean isSDPresent = android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);

        Cursor cursor1 = null;
        Cursor cursor2;
        Cursor cursor;

        if(isSDPresent){
            cursor1 = loadCursor(1);
        }

        cursor2 = loadCursor(2);

        //Load Data From External Storage
        if(cursor1 != null){
            cursor = cursor1;
            loadData(cursor);
            cursor1.close();
        }

        //Load Data From Internal Storage
        cursor = cursor2;
        loadData(cursor);

        cursor2.close();
        cursor.close();

        return imageListModels;
    }

    private void loadData(Cursor cursor) {
        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int imageTitleColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.TITLE);
            int imageWidthColumIndex = cursor.getColumnIndex(MediaStore.Images.Media.WIDTH);
            int imageHeightColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT);
            int imageTakenDate = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
            int imageSizeColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);

            String widthHeight = cursor.getString(imageWidthColumIndex) + "x"
                    + cursor.getString(imageHeightColumnIndex);
            ImageListModel imageListModel = new ImageListModel();
            imageListModel.setImagePath(cursor.getString(dataColumnIndex));
            imageListModel.setImageTitle(cursor.getString(imageTitleColumnIndex));
            imageListModel.setImageWidthHeight(widthHeight);
            imageListModel.setImageTakenDate(cursor.getString(imageTakenDate));
            imageListModel.setImageSize(cursor.getString(imageSizeColumnIndex));
            imageListModels.add(imageListModel);
        }
    }

    private Cursor loadCursor(int i) {
        if(i == 1){
            return activity.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
                    null, orderBy);
        }else {
            return activity.getContentResolver().query(
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI, projection, null,
                    null, orderBy);
        }
    }

    @Override
    protected void onPostExecute(List<ImageListModel> imageListModels) {
        super.onPostExecute(imageListModels);
        notifyData.notifyData(imageListModels);
    }
}