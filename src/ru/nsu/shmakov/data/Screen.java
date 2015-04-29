package ru.nsu.shmakov.data;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Screen {
    int width;
    int height;
    Double nearPanelDist;
    Double farPanelDist;

    public Screen(int width, int height, Double nearPanelDist, Double farPanelDist) {
        this.width = width;
        this.height = height;
        this.nearPanelDist = nearPanelDist;
        this.farPanelDist = farPanelDist;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getNearPanelDist() {
        return nearPanelDist;
    }

    public void setNearPanelDist(Double nearPanelDist) {
        this.nearPanelDist = nearPanelDist;
    }

    public Double getFarPanelDist() {
        return farPanelDist;
    }

    public void setFarPanelDist(Double farPanelDist) {
        this.farPanelDist = farPanelDist;
    }
}
