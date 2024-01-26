package zensagex.async;

import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SaveSkinAsync extends AsyncTask {

    private final byte[] skinData;
    private final String filePath;

    public SaveSkinAsync(byte[] skinData, String filePath) {
        this.skinData = skinData;
        this.filePath = filePath;
        File fileToDelete = new File(filePath);
        if(fileToDelete.exists()){
            fileToDelete.delete();
        }
    }
    
    @Override
    public void onRun() {
        int height = 64;
        int width = 64;
        switch (skinData.length) {
            case 64 * 32 * 4:
                height = 32;
                width = 64;
                break;
            case 64 * 64 * 4:
                height = 64;
                width = 64;
                break;
            case 128 * 64 * 4:
                height = 64;
                width = 128;
                break;
            case 128 * 128 * 4:
                height = 128;
                width = 128;
                break;
        }

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int index = 0;
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                byte r = skinData[index];
                byte g = skinData[index + 1];
                byte b = skinData[index + 2];
                byte a = (byte) (127 - (skinData[index + 3] >> 1));
                index += 4;

                int color = ((a & 0xFF) << 24) |
                             ((r & 0xFF) << 16) |
                             ((g & 0xFF) << 8) |
                             (b & 0xFF);
                if ((color & 0x00FFFFFF) != 0) {
                    img.setRGB(x, y, color);
                }
            }
        }

        try {
            File file = new File(filePath);
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(Server server) {
        ;
    }
}
