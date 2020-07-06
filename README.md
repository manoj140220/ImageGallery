# ImageGallery
ImageGallery : Load images from device or external storage. The following library helps you to choose image from storage and also you can get list of images, you can use the information to create your custom UI.

# Gradle
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
    
    latest_version : 1.0.2
    
    dependencies {
        implementation 'com.github.manoj140220:ImageGallery:latest_version'
    }

# Usage

# Load image from gallery

    new ImageGallery({ActivityContext}, {CurrentClassObject}, boolean);
    
boolean : If the boolean is set to true -> you will be redirected to image grid '4' view for selecting specifc file
boolean : If the boolean is set to false -> you will get List<Image> data. You can use the same to create custom UI

# Capture image

    new ImageGallery({ActivityContext}, {CurrentClassObject});

Impliment :

{ImageDataNotifier}

    @Override
    public void notifyImageListPath(List<ImageListModel> imageList) {
    /**
     * ImageListModel : model class contains the below object
     * Image Path
     * Image Title
     * Image Width*Height
     * Image Taken Date
     * Image Size
     * */
    }

    @Override
    public void notifySelectedImagePath(String filePath) {
        /**
        * Image selected from galler or Image captured using camera
        * The result file path will be notified here.
        * */
    }

       
# Licence

       Copyright 2020 Manoj DB

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.
