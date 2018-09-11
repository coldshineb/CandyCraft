package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.CandyCraftPreferences;
import com.crypticmushroom.candycraft.event.ClientTick;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ThreadCheckUpdate extends Thread {
    String V = "";
    String MC = "";
    String W = "";

    @Override
    public void run() {
        try {
            if (CandyCraftPreferences.checkForUpdates) {
                File file = new File(Minecraft.getMinecraft().mcDataDir + "/config/CandyCraft/");
                file.mkdirs();

                downloadFile("http://candyversion.tumblr.com/", file.getAbsolutePath() + "/version.txt");

                writeDecoded(file);
                analyse(file.getAbsolutePath() + "/version.txt");

                if (!CandyCraft.VERSION.equals(V) && !CandyCraft.VERSION.contains("inconnu") && !CandyCraft.VERSION.contains("null")) {
                    CandyCraft.setShouldUpdate(true);
                    ClientTick.version = V;
                    ClientTick.mcVersion = MC;
                    ClientTick.words = W;
                }
                ClientTick.threadFinished = true;
            } else {
                CandyCraft.setShouldUpdate(false);
                ClientTick.threadFinished = true;
            }
        } catch (Exception e) {
            CandyCraft.setShouldUpdate(false);
            ClientTick.threadFinished = true;
        }
    }

    private void writeDecoded(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath() + "/version.txt"));

        String text = "";
        for (String line; (line = br.readLine()) != null; ) {
            text += line;
        }
        String[] table = text.split("%%%");

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath() + "/version.txt"));
        writer.write(table[1]);
        writer.close();
        br.close();
    }

    private void analyse(String str) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(str));
        String version = br.readLine();
        String[] table = version.split("\\|\\/\\|");
        V = table[0];
        MC = table[1];
        W = table[2];
        br.close();
        new File(str).delete();
    }

    private void downloadFile(String url, String location) throws Exception {
        URL website;
        website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(location);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
    }
}
