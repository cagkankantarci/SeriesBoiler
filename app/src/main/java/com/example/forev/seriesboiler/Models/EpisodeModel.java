package com.example.forev.seriesboiler.Models;

public class EpisodeModel {

    private String episode;

    public EpisodeModel(String episode) {
        this.episode = episode;
    }

    public EpisodeModel()
    {

    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return "EpisodeModel{" +
                "episode='" + episode + '\'' +
                '}';
    }
}
