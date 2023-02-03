package t3.mongorest.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Partida {
    private Integer id;
    private String user;
    private int puntuazioa;
    private java.sql.Timestamp data;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public int getPuntuazioa() {
        return puntuazioa;
    }
    public void setPuntuazioa(int puntuazioa) {
        this.puntuazioa = puntuazioa;
    }
    public java.sql.Timestamp getData() {
        return data;
    }
    public void setData(java.sql.Timestamp dataPartida) {
        this.data = dataPartida;
    }
    @Override
    public String toString() {
        return "Partida [id=" + id + ", user=" + user + ", puntuazioa=" + puntuazioa + ", data=" + data + "]";
    }
}
