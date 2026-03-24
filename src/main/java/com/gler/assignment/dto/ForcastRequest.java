package com.gler.assignment.dto;

public class ForcastRequest {

    private Boolean addTemperature;
    private Boolean addHumidity;
    private Boolean addWindSpeed;

    public Boolean getAddTemperature() {
        return addTemperature;
    }

    public void setAddTemperature(Boolean addTemperature) {
        this.addTemperature = addTemperature;
    }

    public Boolean getAddHumidity() {
        return addHumidity;
    }

    public void setAddHumidity(Boolean addHumidity) {
        this.addHumidity = addHumidity;
    }

    public Boolean getAddWindSpeed() {
        return addWindSpeed;
    }

    public void setAddWindSpeed(Boolean addWindSpeed) {
        this.addWindSpeed = addWindSpeed;
    }
}