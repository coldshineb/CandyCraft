package com.crypticmushroom.candycraft.entity;

public interface IEntityPowerMount
{
	public int maxPower();

	public int powerUsed();

	public void unleashPower();

	public void setPower(int i);

	public int getPower();
}
