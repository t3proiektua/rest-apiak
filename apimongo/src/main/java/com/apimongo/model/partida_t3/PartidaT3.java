package com.apimongo.model.partida_t3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "_class","id" })
public class PartidaT3 {

    public  Integer _id;
    public String erabiltzailea;
    public String data;
    public int puntuazioa;
    public PartidaT3(){}
    //Añadido por Iker
    public PartidaT3(String user, String data, int puntuazioa) {
        this.erabiltzailea = user;
        this.data = data;
        this.puntuazioa = puntuazioa;
    }
    public PartidaT3(int _id, String user, String data, int puntuazioa) {
        this._id = _id;
        this.erabiltzailea = user;
        this.data = data;
        this.puntuazioa = puntuazioa;
    }
    public int getId() {
        return _id;
    }
    public void setId(int id) {
        this._id = id;
    }
    public String getErabiltzailea() {
        return erabiltzailea;
    }
    public void setErabiltzailea(String user) {
        this.erabiltzailea = user;
    }
    public int getPuntuazioa() {
        return puntuazioa;
    }
    public void setPuntuazioa(int puntuazioa) {
        this.puntuazioa = puntuazioa;
    }
    public String getData() {
        return data;
    }
    public void setData(String dataPartida) {
        this.data = dataPartida;
    }
}