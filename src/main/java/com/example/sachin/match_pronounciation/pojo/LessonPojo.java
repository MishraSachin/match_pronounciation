package com.example.sachin.match_pronounciation.pojo;

/**
 * Created by Sachin on 18-01-2018.
 */

public class LessonPojo {

    private String Type;
    private String ConceptName;
    private String Pronounciation;
    private String TargetScript;
    private String AudioUrl;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getConceptName() {
        return ConceptName;
    }

    public void setConceptName(String conceptName) {
        ConceptName = conceptName;
    }

    public String getPronounciation() {
        return Pronounciation;
    }

    public void setPronounciation(String pronounciation) {
        Pronounciation = pronounciation;
    }

    public String getTargetScript() {
        return TargetScript;
    }

    public void setTargetScript(String targetScript) {
        TargetScript = targetScript;
    }

    public String getAudioUrl() {
        return AudioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        AudioUrl = audioUrl;
    }
}
