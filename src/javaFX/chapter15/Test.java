package javaFX.chapter15;

import javafx.scene.image.Image;

public class Test {
    public static void main(String[] args) {

    }

    private static Image[] getImages() {//javaFX/chapter15/resource/image/card/1.png
        Image[] images = new Image[54];
        for (int i = 0; i < images.length; i++) {
            Image image = new Image("javaFX/chapter15/resource/image/card/+ " + (i + 1) + ".png");
            images[i] = image;
        }

        return images;
    }
}
