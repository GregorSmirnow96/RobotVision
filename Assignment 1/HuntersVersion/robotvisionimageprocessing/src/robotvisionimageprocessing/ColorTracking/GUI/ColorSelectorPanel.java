/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author im5no
 */
public class ColorSelectorPanel extends JPanel
{
    private final ColorSliderTextBoxPair redSelector;
    private final ColorSliderTextBoxPair greenSelector;
    private final ColorSliderTextBoxPair blueSelector;
    
    public ColorSelectorPanel()
    {
        this.redSelector = new ColorSliderTextBoxPair();
        this.greenSelector = new ColorSliderTextBoxPair();
        this.blueSelector = new ColorSliderTextBoxPair();
        
        add(redSelector);
        add(greenSelector);
        add(blueSelector);
    }
    
    public Color getSelectedColor()
    {
        return new Color(
            redSelector.getValue(),
            greenSelector.getValue(),
            blueSelector.getValue());
    }
}