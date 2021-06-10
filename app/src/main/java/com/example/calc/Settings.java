package com.example.calc;

import android.os.Parcel;
import android.os.Parcelable;

public class Settings implements Parcelable {
    private int themeId;

    public Settings() {

    }
    public Settings(int themeId) {
        setThemeId(themeId);
    }


    protected Settings(Parcel in) {
        themeId = in.readInt();
    }

    public static final Creator<Settings> CREATOR = new Creator<Settings>() {
        @Override
        public Settings createFromParcel(Parcel in) {
            return new Settings(in);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(themeId);
    }
}
