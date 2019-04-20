package com.crypticmushroom.candycraft.entity;

public interface IEntityPowerMount {
    int maxPower();

    int powerUsed();

    void unleashPower();

    int getPower();

    void setPower(int i);
}
