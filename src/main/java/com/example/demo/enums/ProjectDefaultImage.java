package com.example.demo.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProjectDefaultImage {

    public ProjectDefaultImage(String name){
        this.name = name;
    }

    private String name;

    public String getName(){
        return this.name + ".png";
    }

    public static List<ProjectDefaultImage> getDefaultImage(){

        List<ProjectDefaultImage> pictures = new ArrayList<>();
        pictures.add(new ProjectDefaultImage("apple"));
        pictures.add(new ProjectDefaultImage("banana"));
        pictures.add(new ProjectDefaultImage("chestnut"));
        pictures.add(new ProjectDefaultImage("grapes"));
        pictures.add(new ProjectDefaultImage("oranges"));
        pictures.add(new ProjectDefaultImage("papaja"));
        pictures.add(new ProjectDefaultImage("pears"));
        pictures.add(new ProjectDefaultImage("pineapple"));
        pictures.add(new ProjectDefaultImage("plumps"));
        return pictures;
    }

    public static ProjectDefaultImage getRandomImage(){
        List<ProjectDefaultImage> pictures = getDefaultImage();
        return pictures.get(new Random().nextInt(pictures.size()));
    }
}
