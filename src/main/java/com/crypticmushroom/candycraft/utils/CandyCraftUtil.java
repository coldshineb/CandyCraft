package com.crypticmushroom.candycraft.utils;

public class CandyCraftUtil {
    public static float[] getRGBColorF(int color) {
        float[] rgb = new float[3];

        rgb[0] = ((color >> 16) & 255) / 255f;
        rgb[1] = ((color >> 8) & 255) / 255f;
        rgb[2] = (color & 255) / 255f;

        return rgb;
    }

    public static int getColorCode(float red, float green, float blue) {

        int j1 = (int) (red * 255.0F);
        int k1 = (int) (green * 255.0F);
        int l1 = (int) (blue * 255.0F);
        int j2 = (j1 << 8) + k1;
        j2 = (j2 << 8) + l1;
        return j2;
    }
}
