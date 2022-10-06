package com.andrewn.java2308javafx;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public abstract class SwitchController {
    private Stage currentStage;
    private Scene nextScene;

    private final HashMap<String, Scene> scenes;

    public SwitchController(){
        scenes = new HashMap<>();
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void addScene(String sceneName, Scene newScene) {
        scenes.put(sceneName, newScene);
    }

    public void nextScene() {
        currentStage.setScene(nextScene);
    }

    public void changeScene(String sceneName) {
        if (scenes.containsKey(sceneName)) {
            currentStage.setScene(scenes.get(sceneName));
        }
    }
}
