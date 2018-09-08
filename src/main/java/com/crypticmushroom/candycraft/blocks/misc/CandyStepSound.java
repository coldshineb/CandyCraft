package com.crypticmushroom.candycraft.blocks.misc;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvent;

public class CandyStepSound extends SoundType
{
	public CandyStepSound(SoundEvent ev, float par2, float par3)
	{
		super(par2, par3, ev, ev, ev, ev, ev);
	}
}
