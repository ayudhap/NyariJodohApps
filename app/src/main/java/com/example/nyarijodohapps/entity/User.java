package com.example.nyarijodohapps.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @SerializedName("id")
    private long id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("nama")
    private String nama;
    @SerializedName("jenis_kelamin")
    private String jenisKelamin;
    @SerializedName("no_hp")
    private String noHp;
    @SerializedName("umur")
    private String umur;
    @SerializedName("foto")
    private String foto;

    public User(long id, String username, String password, String nama, String jenisKelamin, String noHp, String umur, String foto) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.noHp = noHp;
        this.umur = umur;
        this.foto = foto;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.nama);
        dest.writeString(this.jenisKelamin);
        dest.writeString(this.noHp);
        dest.writeString(this.umur);
        dest.writeString(this.foto);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.username = source.readString();
        this.password = source.readString();
        this.nama = source.readString();
        this.jenisKelamin = source.readString();
        this.noHp = source.readString();
        this.umur = source.readString();
        this.foto = source.readString();
    }

    protected User(Parcel in) {
        this.id = in.readLong();
        this.username = in.readString();
        this.password = in.readString();
        this.nama = in.readString();
        this.jenisKelamin = in.readString();
        this.noHp = in.readString();
        this.umur = in.readString();
        this.foto = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
