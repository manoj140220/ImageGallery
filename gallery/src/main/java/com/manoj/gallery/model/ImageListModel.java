package com.manoj.gallery.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created By : Manoj DB on 29/6/20
 */
public class ImageListModel implements Parcelable {
    private String imagePath;
    private String imageTitle;
    private String imageWidthHeight;
    private String imageTakenDate;
    private String imageSize;

    public ImageListModel() {
        /*
         * Public Default Constructor
         * */
    }

    private ImageListModel(Parcel in) {
        imagePath = in.readString();
        imageTitle = in.readString();
        imageWidthHeight = in.readString();
        imageTakenDate = in.readString();
        imageSize = in.readString();
    }

    public static final Creator<ImageListModel> CREATOR = new Creator<ImageListModel>() {
        @Override
        public ImageListModel createFromParcel(Parcel in) {
            return new ImageListModel(in);
        }

        @Override
        public ImageListModel[] newArray(int size) {
            return new ImageListModel[size];
        }
    };

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public void setImageWidthHeight(String imageWidthHeight) {
        this.imageWidthHeight = imageWidthHeight;
    }

    public void setImageTakenDate(String imageTakenDate) {
        this.imageTakenDate = imageTakenDate;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageWidthHeight() {
        return imageWidthHeight;
    }

    public String getImageTakenDate() {
        return imageTakenDate;
    }

    public String getImageSize() {
        return imageSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
        dest.writeString(imageTitle);
        dest.writeString(imageWidthHeight);
        dest.writeString(imageTakenDate);
        dest.writeString(imageSize);
    }
}