package com.ynwa.kdl.hosein.shopping.realm_db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Detail extends RealmObject {

    @PrimaryKey
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
    private RealmList<String> sensors;
    private String os;
    private String battery;
    private RealmList<String> features;


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

    public RealmList<String> getSensors() {
        return sensors;
    }

    public void setSensors(RealmList<String> sensors) {
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

    public RealmList<String> getFeatures() {
        return features;
    }

    public void setFeatures(RealmList<String> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", phoneSize='" + phoneSize + '\'' +
                ", simSize='" + simSize + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
