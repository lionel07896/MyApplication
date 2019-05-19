package com.example.myapplication;

public class Aktivitaet {
    private String Zeit;
    private String Ort;
    private String Name;
    private String Id;

    public Aktivitaet (String Name, String Ort, String Zeit, long Id)
    {
        this.Zeit = Zeit;
        this.Ort = Ort;
        this.Name= Name ;
        this.Id = String.valueOf(Id) ;
    }

    public void setZeit(String zeit) {
        Zeit = zeit;
    }

    public void setOrt(String ort) {
        Ort = ort;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getZeit() {
        return Zeit;
    }

    public String getOrt() {
        return Ort;
    }

    public String getName() {
        return Name;
    }

    public String getId() {
        return Id;
    }
    public Aktivitaet () {

    }
}
