package com.ynwa.kdl.hosein.shopping.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DetailParcel implements Parcelable{

    private long id;
    private String phoneSize;
    private String simSize;
    private String weight;
    private String simNumber;
    private String ramSize;
    private String ramType;
    private String storageSize;
    private String cpuChip;
    private String cpuName;
    private String cpuType;
    private String cpuFrequency;
    private String gpu;
    private String screenType;
    private String screenSize;
    private String screenResolution;
    private String screenPixel;
    private String cameraFront;
    private String cameraMain;
    private String cameraFilmed;
    private List<String> sensors;
    private String os;
    private String battery;
    private List<String> features;


    public DetailParcel() {
    }

    protected DetailParcel(Parcel in) {
        id = in.readLong();
        phoneSize = in.readString();
        simSize = in.readString();
        weight = in.readString();
        simNumber = in.readString();
        ramSize = in.readString();
        ramType = in.readString();
        storageSize = in.readString();
        cpuChip = in.readString();
        cpuName = in.readString();
        cpuType = in.readString();
        cpuFrequency = in.readString();
        gpu = in.readString();
        screenType = in.readString();
        screenSize = in.readString();
        screenResolution = in.readString();
        screenPixel = in.readString();
        cameraFront = in.readString();
        cameraMain = in.readString();
        cameraFilmed = in.readString();
        sensors = in.createStringArrayList();
        os = in.readString();
        battery = in.readString();
        features = in.createStringArrayList();
    }

    public static final Creator<DetailParcel> CREATOR = new Creator<DetailParcel>() {
        @Override
        public DetailParcel createFromParcel(Parcel in) {
            return new DetailParcel(in);
        }

        @Override
        public DetailParcel[] newArray(int size) {
            return new DetailParcel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneSize() {
        return phoneSize;
    }

    public void setPhoneSize(String phoneSize) {
        this.phoneSize = phoneSize;
    }

    public String getSimSize() {
        return simSize;
    }

    public void setSimSize(String simSize) {
        this.simSize = simSize;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(String storageSize) {
        this.storageSize = storageSize;
    }

    public String getCpuChip() {
        return cpuChip;
    }

    public void setCpuChip(String cpuChip) {
        this.cpuChip = cpuChip;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public String getCpuFrequency() {
        return cpuFrequency;
    }

    public void setCpuFrequency(String cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getScreenPixel() {
        return screenPixel;
    }

    public void setScreenPixel(String screenPixel) {
        this.screenPixel = screenPixel;
    }

    public String getCameraFront() {
        return cameraFront;
    }

    public void setCameraFront(String cameraFront) {
        this.cameraFront = cameraFront;
    }

    public String getCameraMain() {
        return cameraMain;
    }

    public void setCameraMain(String cameraMain) {
        this.cameraMain = cameraMain;
    }

    public String getCameraFilmed() {
        return cameraFilmed;
    }

    public void setCameraFilmed(String cameraFilmed) {
        this.cameraFilmed = cameraFilmed;
    }

    public List<String> getSensors() {
        return sensors;
    }

    public void setSensors(List<String> sensors) {
        this.sensors = sensors;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(phoneSize);
        dest.writeString(simSize);
        dest.writeString(weight);
        dest.writeString(simNumber);
        dest.writeString(ramSize);
        dest.writeString(ramType);
        dest.writeString(storageSize);
        dest.writeString(cpuChip);
        dest.writeString(cpuName);
        dest.writeString(cpuType);
        dest.writeString(cpuFrequency);
        dest.writeString(gpu);
        dest.writeString(screenType);
        dest.writeString(screenSize);
        dest.writeString(screenResolution);
        dest.writeString(screenPixel);
        dest.writeString(cameraFront);
        dest.writeString(cameraMain);
        dest.writeString(cameraFilmed);
        dest.writeStringList(sensors);
        dest.writeString(os);
        dest.writeString(battery);
        dest.writeStringList(features);
    }
}
