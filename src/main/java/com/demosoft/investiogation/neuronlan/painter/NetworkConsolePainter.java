package com.demosoft.investiogation.neuronlan.painter;

import com.demosoft.investiogation.neuronlan.InitializationManager;
import com.demosoft.investiogation.neuronlan.PlayerBrain;
import com.demosoft.investiogation.neuronlan.entity.newgen.Input;
import com.demosoft.investiogation.neuronlan.entity.newgen.Link;
import com.demosoft.investiogation.neuronlan.entity.newgen.Neuron;
import com.demosoft.investiogation.neuronlan.entity.newgen.Output;
import com.demosoft.investiogation.neuronlan.painter.entity.PainterInput;
import com.demosoft.investiogation.neuronlan.painter.entity.PainterLink;
import com.demosoft.investiogation.neuronlan.painter.entity.PainterNeuron;
import com.demosoft.investiogation.neuronlan.painter.entity.PainterOutput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class NetworkConsolePainter extends JPanel {

    public static final int step = 200;
    public static final int start = 100;
    public static final int rad = 30;
    public static final Point startPoint = new Point(start, start);

    private Map<String, PainterInput> painterInputMap = new HashMap<>();
    private Map<String, PainterNeuron> painterNeuronMap = new HashMap<>();
    private Map<String, PainterLink> painterLinkMap = new HashMap<>();
    private Map<String, PainterOutput> painterOutputMap = new HashMap<>();


    private PlayerBrain playerBrain = null;

    public NetworkConsolePainter(PlayerBrain playerBrain) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 800));
        this.playerBrain = playerBrain;
    }

    NetworkConsolePainter() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 500));
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawNetwork(g);
    }

    private void drawNetwork(Graphics g) {
        int max = NetworkConsolePainter.findMax(playerBrain);
        int heightBuf = -1;
        Point pointBuf = null;
        switch (max) {
            case 0:
                pointBuf = startPoint;
                heightBuf = drawInputs(g, pointBuf, -1, true);
                pointBuf = nextGorzontPoint(pointBuf);
                drawNeuron(g, pointBuf, heightBuf, false);
                pointBuf = nextGorzontPoint(pointBuf);
                drawOutputs(g, pointBuf, heightBuf, false);
                break;
            case 1:
                pointBuf = nextGorzontPoint(startPoint);
                heightBuf = drawNeuron(g, pointBuf, -1, true);
                pointBuf = nextGorzontPoint(pointBuf);
                drawOutputs(g, pointBuf, heightBuf, false);
                pointBuf = startPoint;
                drawInputs(g, pointBuf, heightBuf, false);
                break;
            case 2:
                pointBuf = nextGorzontPoint(startPoint, 2);
                heightBuf = drawOutputs(g, pointBuf, -1, true);
                pointBuf = prevGorzontPoint(pointBuf);
                drawNeuron(g, pointBuf, heightBuf, false);
                pointBuf = prevGorzontPoint(pointBuf);
                drawInputs(g, pointBuf, heightBuf, false);
                break;
        }
        drawLinks(g);

    }

    private Point nextGorzontPoint(Point point) {
        return new Point(((Double) point.getX()).intValue() + step, ((Double) point.getY()).intValue());
    }

    private Point nextGorzontPoint(Point point, int stepCount) {
        return new Point(((Double) point.getX()).intValue() + step * stepCount, ((Double) point.getY()).intValue());
    }

    private Point prevGorzontPoint(Point point) {
        return new Point(((Double) point.getX()).intValue() - step, ((Double) point.getY()).intValue());
    }

    private void drawLinks(Graphics g) {
        for (Input input : playerBrain.getInputs()) {
            Point startPoint = painterInputMap.get(input.getId()).getPoint();
            startPoint = new Point((int) startPoint.getX() + rad, (int) startPoint.getY());
            List<Link> links = input.getOutgoingLinks();
            drawLinks(g, startPoint, links);
        }

        for (Neuron neuron : playerBrain.getNeurons()) {
            Point startPoint = painterNeuronMap.get(neuron.getId()).getPoint();
            startPoint = new Point((int) startPoint.getX() + rad, (int) startPoint.getY());
            List<Link> links = neuron.getOutgoingLinks();
            drawLinks(g, startPoint, links);
        }
    }

    private void drawLinks(Graphics g, Point startPoint, List<Link> links) {
        for (Link link : links) {
            Point endPoint = null;
            if (link.getLinkType() == Link.LinkType.NEURON) {
                endPoint = painterNeuronMap.get(link.getNeuron().getId()).getPoint();
            } else {
                endPoint = painterOutputMap.get(link.getOutput().getId()).getPoint();
            }
            endPoint = new Point((int) endPoint.getX() - rad, (int) endPoint.getY());
            drawLine(g, startPoint, endPoint);
        }
    }

    public static void drawLine(Graphics g, Point startPoint, Point endPoint) {
        g.drawLine((int) startPoint.getX(), (int) startPoint.getY(), (int) endPoint.getX(), (int) endPoint.getY());
    }

    private int drawInputs(Graphics g, Point start, int maxHeight, boolean first) {
        int yArrow = ((Double) start.getY()).intValue();
        int xArrow = ((Double) start.getX()).intValue();
        int itemsCount = playerBrain.getInputs().size();
        int localStep = getLocalStep(maxHeight, first, itemsCount);
        for (Input input : playerBrain.getInputs()) {
            drawCircle(g, xArrow, yArrow, rad);
            g.drawString(input.getId(), xArrow - 15, yArrow - 30);
            g.drawString("Input", xArrow - 12, yArrow + 5);
            painterInputMap.put(input.getId(), new PainterInput(input, new Point(xArrow, yArrow)));
            yArrow += localStep;
        }
        if (first) {
            return step * itemsCount;
        }
        return -1;
    }

    private int drawNeuron(Graphics g, Point start, int maxHeight, boolean first) {
        int yArrow = ((Double) start.getY()).intValue();
        int xArrow = ((Double) start.getX()).intValue();
        int itemsCount = playerBrain.getNeurons().size();
        int localStep = getLocalStep(maxHeight, first, itemsCount);
        for (Neuron neuron : playerBrain.getNeurons()) {
            drawCircle(g, xArrow, yArrow, rad);
            g.drawString(neuron.getId(), xArrow - 15, yArrow - 30);
            g.drawString("Neuron", xArrow - 12, yArrow + 5);
            painterNeuronMap.put(neuron.getId(), new PainterNeuron(neuron, new Point(xArrow, yArrow)));
            yArrow += localStep;
        }
        if (first) {
            return step * itemsCount;
        }
        return -1;
    }

    private int drawOutputs(Graphics g, Point start, int maxHeight, boolean first) {
        int yArrow = ((Double) start.getY()).intValue();
        int xArrow = ((Double) start.getX()).intValue();
        int itemsCount = playerBrain.getOutputs().size();
        int localStep = getLocalStep(maxHeight, first, itemsCount);
        for (Output output : playerBrain.getOutputs()) {
            drawCircle(g, xArrow, yArrow, rad);
            g.drawString(output.getId(), xArrow - 15, yArrow - 30);
            g.drawString("Output", xArrow - 12, yArrow + 5);
            painterOutputMap.put(output.getId(), new PainterOutput(output, new Point(xArrow, yArrow)));
            yArrow += localStep;
        }
        if (first) {
            return step * itemsCount;
        }
        return -1;
    }

    private int getLocalStep(int maxHeight, boolean first, int count) {
        int localStep;
        if (first) {
            localStep = step;
        } else {
            localStep = calculateStep(count, maxHeight);
        }
        return localStep;
    }

    private int calculateStep(int count, int maxHeight) {
        return maxHeight / count;
    }

    private void drawCircle(Graphics g, int x, int y, int radius) {
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

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

        frame.getContentPane().add(new NetworkConsolePainter(brain));
        frame.pack();
        frame.setVisible(true);
    }

}


