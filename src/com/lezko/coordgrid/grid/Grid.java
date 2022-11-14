package com.lezko.coordgrid.grid;

import com.lezko.coordgrid.drawer.BHLineDrawer;
import com.lezko.coordgrid.drawer.LineDrawer;
import com.lezko.coordgrid.drawer.PixelDrawer;
import com.lezko.coordgrid.drawer.TextDrawer;
import com.lezko.coordgrid.geometry.Object;

import java.awt.*;

public class Grid implements Object {

    private static final Color DEFAULT_LINE_COLOR = new Color(0, 0, 0, 26);
    private Color lineColor = DEFAULT_LINE_COLOR;
    private final double E = 1e-5;

    // for client
    private final int minSize, maxSize;
    private double step;

    private double resizeCoefficient = 2.0;
    private boolean xAxisEnabled = true;
    private boolean yAxisEnabled = true;

    private final TextDrawer textDrawer;

    private Runnable beforeRender, afterRender;

    public Grid(double step, int minSize, int maxSize, TextDrawer textDrawer) {
        this.step = step;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.textDrawer = textDrawer;
    }

    private void updateStep(double scale) {
        while (step * scale > maxSize) {
            step /= resizeCoefficient;
        }
        while (step * scale < minSize) {
            step *= resizeCoefficient;
        }
    }

    @Override
    public void render(
        PixelDrawer pixelDrawer,
        double screenX,
        double screenY,
        int screenWidth,
        int screenHeight,
        double scale,
        boolean xInverted,
        boolean yInverted
    ) {
        if (beforeRender != null) {
            beforeRender.run();
        }
        updateStep(scale);

        LineDrawer lineDrawer = new BHLineDrawer(pixelDrawer);

        int xAxisCoord = 0;
        if (xAxisEnabled && screenY < 0 && 0 < screenY + screenHeight / scale) {
            xAxisCoord += (int) (-screenY * scale);
            lineDrawer.drawLine(0, xAxisCoord + 1, screenWidth, xAxisCoord + 1, lineColor);
            lineDrawer.drawLine(0, xAxisCoord - 1, screenWidth, xAxisCoord - 1, lineColor);
        }

        int yAxisCoord = 0;
        if (yAxisEnabled && screenX < 0 && 0 < screenX + screenWidth / scale) {
            yAxisCoord += (int) (-screenX * scale);
            lineDrawer.drawLine(yAxisCoord + 1, 0, yAxisCoord + 1, screenHeight, lineColor);
            lineDrawer.drawLine(yAxisCoord - 1, 0, yAxisCoord - 1, screenHeight, lineColor);
        }

        double x = (screenX - screenX % step - step);
        for (double i = -((screenX % step) * scale); i < screenWidth; i += step * scale) {
            lineDrawer.drawLine((int) i, 0, (int) i, screenHeight, lineColor);
            textDrawer.drawText((int) i, xAxisCoord + 15, 10, String.valueOf((x += step) * (xInverted ? -1 : 1)), lineColor);
        }
        double y = (screenY - screenY % step - step);
        for (double j = -((screenY % step) * scale); j < screenHeight; j += step * scale) {
            lineDrawer.drawLine(0, (int) j, screenWidth, (int) j, lineColor);
            textDrawer.drawText(yAxisCoord + 5, (int) j, 10, String.valueOf((y += step) * (yInverted ? -1 : 1)), lineColor);
        }

        int XX1 = screenWidth - 45, XY1 = screenHeight - 35, XX2 = screenWidth - 15, XY2 = screenHeight - 35;
        int YX1 = screenWidth - 30, YY1 = screenHeight - 50, YX2 = screenWidth - 30, YY2 = screenHeight - 20;
        lineDrawer.drawLine(XX1, XY1, XX2, XY2, lineColor);
        lineDrawer.drawLine(YX1, YY1, YX2, YY2, lineColor);
        if (xInverted) {
            lineDrawer.drawLine(XX1, XY1, XX1 + 3, XY1 - 3, lineColor);
            lineDrawer.drawLine(XX1, XY1, XX1 + 3, XY1 + 3, lineColor);
        } else {
            lineDrawer.drawLine(XX2, XY2, XX2 - 3, XY2 - 3, lineColor);
            lineDrawer.drawLine(XX2, XY2, XX2 - 3, XY2 + 3, lineColor);
        }
        if (yInverted) {
            lineDrawer.drawLine(YX1, YY1, YX1 - 3, YY1 + 3, lineColor);
            lineDrawer.drawLine(YX1, YY1, YX1 + 3, YY1 + 3, lineColor);
        } else {
            lineDrawer.drawLine(YX2, YY2, YX2 - 3, YY2 - 3, lineColor);
            lineDrawer.drawLine(YX2, YY2, YX2 + 3, YY2 - 3, lineColor);
        }

        if (afterRender != null) {
            afterRender.run();
        }
    }

    public void setYAxisEnabled(boolean xAxisEnabled) {
        this.xAxisEnabled = xAxisEnabled;
    }

    public void setXAxisEnabled(boolean yAxisEnabled) {
        this.yAxisEnabled = yAxisEnabled;
    }

    public void setBeforeRender(Runnable beforeRender) {
        this.beforeRender = beforeRender;
    }

    public void setAfterRender(Runnable afterRender) {
        this.afterRender = afterRender;
    }
}
