package backend.model;

import backend.EffectState;
import backend.RGBColor;

public abstract class FigureImpl implements Figure {
    private boolean shadow, gradient, bevel;
    private RGBColor RGBColor;

    public void setFillColor(RGBColor RGBColor) {
        this.RGBColor = RGBColor;
    }

    public RGBColor getColor() {
        return RGBColor;
    }

    public void addShadow() {
        shadow = true;
    }

    public EffectState stateShadow() {
        //todo
        System.out.println(shadow ? "true" : "false");
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
