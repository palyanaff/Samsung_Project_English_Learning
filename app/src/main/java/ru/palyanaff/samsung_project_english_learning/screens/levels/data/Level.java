package ru.palyanaff.samsung_project_english_learning.screens.levels.data;

/**
 * Class level contain Id and header text
 */
public class Level {

    private final int levelId;
    private final int header;

    Level(int levelId, int header){
        this.levelId = levelId;
        this.header = header;
    }

    public int getLevelId(){
        return levelId;
    }

    public int getHeader(){
        return header;
    }
}
