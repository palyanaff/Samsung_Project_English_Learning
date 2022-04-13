package ru.palyanaff.samsung_project_english_learning.screens.levels.data;

/**
 * Class level contain Id and header text
 */
public class Level {

    private final String levelId;
    private final String header;

    Level(String levelId, String header){
        this.levelId = levelId;
        this.header = header;
    }

    public String getLevelId(){
        return levelId;
    }

    public String getHeader(){
        return header;
    }
}
