package com.manoj.gallery.library;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * Created By : Manoj DB on 1/7/20
 */
public class CreateFile {

    public CreateFile() {
        /**
         * Default constructor
         * */
    }

    public File getFileDirectory(){
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "mGallery/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public String getImageName(){
        return System.currentTimeMillis() + ".jpg";
    }

    public Uri getImageUri(File file, Activity activity){
        return FileProvider.getUriForFile(
                activity,
                activity.getApplicationContext().getPackageName()+".provider",
                file);
    }
}