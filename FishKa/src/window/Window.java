package window;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.*;

import constants.*;
import game.Fish;
import input.Input;
import utils.Time;

public class Window{

    private static boolean created	= false;
    private static JFrame window;
    private static Canvas content;

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics	bufferGraphics;
    private static int clearColor;

    private static BufferStrategy bufferStrategy;

    public static void create(int width, int height, String title, int _clearColor, int numBuffers) {

        if (created)
            return;

        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = _clearColor;

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;
    }

    public static void destroy() {
        if (!created)
            return;

        window.dispose();
    }

    public static void setTitle(int fps, int upd, int updl) {
        window.setTitle(constants.WindowConstants.TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
    }

    public static void scoreRender(Graphics2D graphics, int score, int scoreWin){
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 20));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Score: " + score + " / " + scoreWin, 20, 30);
    }

    public static void renderSharkWarning(Graphics2D graphics, Fish fish){
        Color color = new Color(128, 56, 54, 150);
        graphics.setColor(color);
        graphics.fillRoundRect(fish.getX(), fish.getY(), FishTypeConstants.SHARK_SPRITE_SIZE, FishTypeConstants.SHARK_SPRITE_SIZE, 50, 50);

        graphics.setFont(new Font("TimesRoman", Font.BOLD, 40));
        graphics.drawString("SHARKS ARE COMING!!!", 380, 380);
    }

    public static void renderLevelNumber(int levelNumber, Graphics2D graphics){
        Color color = new Color(128, 56, 54, 150);
        graphics.setColor(color);
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 60));
        graphics.drawString("LEVEL " + levelNumber, 450, 380);
    }

    public static void addInputListener(Input inputListener) {
        window.add(inputListener);
    }

}