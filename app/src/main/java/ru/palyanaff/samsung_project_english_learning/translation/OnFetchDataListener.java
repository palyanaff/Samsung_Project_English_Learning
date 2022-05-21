package ru.palyanaff.samsung_project_english_learning.translation;

import ru.palyanaff.samsung_project_english_learning.translation.models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
