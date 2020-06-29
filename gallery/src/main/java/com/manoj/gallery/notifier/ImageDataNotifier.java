package com.manoj.gallery.notifier;

import com.manoj.gallery.model.ImageListModel;

import java.util.List;

/**
 * Created By : Manoj DB on 29/6/20
 */
public interface ImageDataNotifier {
    void notifyImageListPath(List<ImageListModel> imageList);
    void notifySelectedImagePath(String filePath);
}