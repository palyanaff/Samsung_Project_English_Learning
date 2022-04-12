package ru.palyanaff.samsung_project_english_learning.screens.levels.data;

import java.util.ArrayList;

import ru.palyanaff.samsung_project_english_learning.R;

/**
 * Class which work with data of levels
 */
public class Datasource {
    /**
     * Load levels recourse
     * @return list with data(¿numbers?) of levels
     */
    public ArrayList<Level> loadLevel() {
        // TODO: Need to update (load from server or add normal string resource)
        ArrayList<Level> list = new ArrayList<>();

        // TODO: make normal counter of levels(Id)
        list.add(new Level(1, R.string.messages_header));
        return list;
    }
}
