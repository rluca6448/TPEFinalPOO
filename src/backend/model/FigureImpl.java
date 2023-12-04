package backend.model;
import backend.EffectState;
import javafx.scene.paint.Color;
public abstract class FigureImpl implements Figure{
    private boolean shadow, gradient, bevel;
    private Color color;
    public void setFillColor(Color color){
        this.color = color;
    }
    public Color getColor(){
        return color;
    }

    public void addShadow(){
        shadow = true;
    }
    public EffectState stateShadow(){
        //todo
        System.out.println(shadow ? "true" : "false");
        return shadow ?  EffectState.TRUE : EffectState.FALSE;
    }
    public void deleteShadow(){
        shadow = false;
    }

    public void addGradient(){
        gradient = true;
    }
    public EffectState stateGradient(){
        return gradient ?  EffectState.TRUE : EffectState.FALSE;
    }
    public void deleteGradient(){
        gradient = false;
    }

    public void addBevel(){
        bevel = true;
    }
    public EffectState stateBevel(){
        return bevel ?  EffectState.TRUE : EffectState.FALSE;
    }
    public void deleteBevel(){
        bevel = false;
    }
}
