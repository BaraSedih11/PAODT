package com.example.project286;

public class KidneyTests {
    private String rec_id;
    private String serum;
    private int hla;
    private String ahla;
    private String ecg;
    private String echo;
    private String notFit;
    private String noHardDiseases;

    private int score;

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHla() {
        return hla;
    }

    public void setHla(int hla) {
        this.hla = hla;
    }

    public String getSerum() {
        return serum;
    }

    public String getAhla() {
        return ahla;
    }

    public String getEcg() {
        return ecg;
    }

    public String getEcho() {
        return echo;
    }

    public String getNotFit() {
        return notFit;
    }

    public String getNoHardDiseases() {
        return noHardDiseases;
    }

    public void setSerum(String serum) {
        this.serum = serum;
    }

    public void setAhla(String ahla) {
        this.ahla = ahla;
    }

    public void setEcg(String ecg) {
        this.ecg = ecg;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }

    public void setNotFit(String notFit) {
        this.notFit = notFit;
    }

    public void setNoHardDiseases(String noHardDiseases) {
        this.noHardDiseases = noHardDiseases;
    }
}
