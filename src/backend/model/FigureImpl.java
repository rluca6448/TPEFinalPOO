package backend.model;

import backend.RGBColor;


public abstract class FigureImpl implements Figure {

    private boolean shadow, gradient, bevel;

    private final RGBColor color;


    protected FigureImpl(RGBColor color) {
        this.color = color;
        shadow = false;
        gradient = false;
        bevel = false;
    }

    public RGBColor getColor() {
        return color;
    }

    public void addShadow() {
        shadow = true;
    }

    public boolean hasShadow() {
        return shadow;
    }

    public void deleteShadow() {
        shadow = false;
    }

    public void addGradient() {
        gradient = true;
    }

    public boolean hasGradient() {
        return gradient;
    }

    public void deleteGradient() {
        gradient = false;
    }

    public void addBevel() {
        bevel = true;
    }

    public boolean hasBevel() {
        return bevel;
    }

    public void deleteBevel() {
        bevel = false;
    }
}
