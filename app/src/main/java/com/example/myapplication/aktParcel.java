package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class aktParcel extends Aktivitaet implements Parcelable {



    @Override
    public int describeContents() {
        return 0;
    }

    protected aktParcel(Parcel in) {
        this.setId(in.readString());
        this.setName(in.readString());
        this.setOrt(in.readString());
        this.setZeit(in.readString());

    }

    public aktParcel(String id, String name, String ort, String zeit) {
        setId(id);
        setName(name);
        setOrt(ort);
        setZeit(zeit);
    }

    public static final Creator<aktParcel> CREATOR = new Creator<aktParcel>() {
        @Override
        public aktParcel createFromParcel(Parcel source) {
            return new aktParcel(source);
        }

        @Override
        public aktParcel[] newArray(int size) {
            return new aktParcel[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getId());
        dest.writeString(this.getName());
        dest.writeString(this.getOrt());
        dest.writeString(this.getZeit());

    }

}
