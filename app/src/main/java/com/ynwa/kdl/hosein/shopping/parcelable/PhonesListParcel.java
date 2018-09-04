package com.ynwa.kdl.hosein.shopping.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PhonesListParcel implements Parcelable{

    private List<PhoneParcel> parcelList;

    public PhonesListParcel(List<PhoneParcel> parcelList) {
        this.parcelList = parcelList;
    }

    protected PhonesListParcel(Parcel in) {
        parcelList = in.createTypedArrayList(PhoneParcel.CREATOR);
    }

    public static final Creator<PhonesListParcel> CREATOR = new Creator<PhonesListParcel>() {
        @Override
        public PhonesListParcel createFromParcel(Parcel in) {
            return new PhonesListParcel(in);
        }

        @Override
        public PhonesListParcel[] newArray(int size) {
            return new PhonesListParcel[size];
        }
    };

    public List<PhoneParcel> getParcelList() {
        return parcelList;
    }

    public void setParcelList(List<PhoneParcel> parcelList) {
        this.parcelList = parcelList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(parcelList);
    }
}
