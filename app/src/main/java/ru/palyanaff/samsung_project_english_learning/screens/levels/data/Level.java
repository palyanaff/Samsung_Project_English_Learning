package ru.palyanaff.samsung_project_english_learning.screens.levels.data;

import java.util.ArrayList;

/**
 * Class level contain Id and header text
 */
public class Level {

    private final String levelId;
    private final String header;
    private final String[] taskArr = new String[3];
    private final String taskHeader;
    private final String taskText;
    private final String taskAnswer;

    public String[] getTaskArr() {
        return taskArr;
    }

    Level(String levelId, String header, String taskHeader, String taskText, String taskAnswer){
        this.levelId = levelId;
        this.header = header;
        this.taskHeader = taskHeader;
        this.taskText = taskText;
        this.taskAnswer = taskAnswer;
        taskArr[0] = taskHeader;
        taskArr[1] = taskText;
        taskArr[2] = taskAnswer;
    }

    public String getLevelId(){
        return levelId;
    }

    public String getHeader(){
        return header;
    }

    public String getTaskHeader() {
        return taskHeader;
    }

    public String getTaskText() {
        return taskText;
    }

    public String getTaskAnswer() {
        return taskAnswer;
    }

    /*class Task{
        private final String taskHeader;
        private final String taskText;
        private final String taskAnswer;

        Task(String taskHeader, String taskText, String taskAnswer){
            this.taskHeader = taskHeader;
            this.taskText = taskText;
            this.taskAnswer = taskAnswer;
        }

        public String getTaskHeader() {
            return taskHeader;
        }

        public String getTaskText() {
            return taskText;
        }

        public String getTaskAnswer() {
            return taskAnswer;
        }
    }*/

}
