package ru.palyanaff.samsung_project_english_learning.screens.levels.data;

/**
 * Class level contain Id and header text
 */
public class Level {

    private final String levelId;
    private final String header;

<<<<<<< Updated upstream
    Level(String levelId, String header){
=======
    public Level(String levelId, String header, String taskHeader, String taskText, String taskAnswer){
>>>>>>> Stashed changes
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
