package backend.model;
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
    public boolean hasShadow(){
        return shadow;
    }
    public void deleteShadow(){
        shadow = false;
    }

    public void addGradient(){
        gradient = true;
    }
    public boolean hasGradient(){
        return gradient;
    }
    public void deleteGradient(){
        gradient = false;
    }

    public void addBevel(){
        bevel = true;
    }
    public boolean hasBevel(){
        return bevel;
    }
    public void deleteBevel(){
        bevel = false;
    }
}
