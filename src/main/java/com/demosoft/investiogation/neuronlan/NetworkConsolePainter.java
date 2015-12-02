package com.demosoft.investiogation.neuronlan;

import com.demosoft.investiogation.neuronlan.entity.newgen.Input;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class NetworkConsolePainter {

    public static final int step = 100;

    public static void draw(PlayerBrain playerBrain) {
        int maxItemsCountIndex = findMax(playerBrain);
        System.out.println(maxItemsCountIndex);
    }

    public static int findMax(PlayerBrain playerBrain) {
        List<List> lists = new ArrayList<>(3);
        lists.add(playerBrain.getInputs());
        lists.add(playerBrain.getNeurons());
        lists.add(playerBrain.getOutputs());
        int size = -1;
        List maxlist = null;
        for (List list : lists) {
            if (list.size() > size) {
                maxlist = list;
                size = list.size();
            }
        }
        return lists.indexOf(maxlist);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        PlayerBrain brain = new InitializationManager().initPlayerBrain();

        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new MyPanel(brain));
        frame.pack();
        frame.setVisible(true);
    }

}

class MyPanel extends JPanel {
    public static final int step = 100;

    private PlayerBrain playerBrain = null;

    public MyPanel(PlayerBrain playerBrain) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 500));
        this.playerBrain = playerBrain;
    }

    MyPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 500));
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawNetwork(g);
    }

    private void drawNetwork(Graphics g) {
        NetworkConsolePainter.findMax(playerBrain);
        drawInputs(g);

    }

    private void drawInputs(Graphics g) {
        int yArrow = 100;
        for (Input input : playerBrain.getInputs()) {
            drawCircle(g,100 , yArrow, 25);
            g.drawString(input.getId(), 100 - 12, yArrow - 30);
            yArrow += step;
        }

    }

    private void drawCircle(Graphics g, int x, int y, int radius) {
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
