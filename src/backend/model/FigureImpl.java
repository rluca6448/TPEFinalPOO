package backend.model;

import backend.EffectState;
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

    public EffectState stateShadow() {
        return shadow ? EffectState.TRUE : EffectState.FALSE;
    }

    public void deleteShadow() {
        shadow = false;
    }

    public void addGradient() {
        gradient = true;
    }

    public EffectState stateGradient() {
        return gradient ? EffectState.TRUE : EffectState.FALSE;
    }

    public void deleteGradient() {
        gradient = false;
    }

    public void addBevel() {
        bevel = true;
    }

    public EffectState stateBevel() {
        return bevel ? EffectState.TRUE : EffectState.FALSE;
    }

    public void deleteBevel() {
        bevel = false;
    }
}
