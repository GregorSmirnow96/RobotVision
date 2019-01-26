/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI;

import javax.swing.JPanel;

/**
 *
 * @author im5no
 */
public class ColorSliderTextBoxPair extends JPanel
{
    
    private final ColorSlider slider;
    private final ColorSelectionTextField textField;
    
    public ColorSliderTextBoxPair()
    {
        this.slider = new ColorSlider();
        this.textField = new ColorSelectionTextField();
        
        slider.register(textField);
        textField.register(slider);
        
        add(slider);
        add(textField);
    }
    
    public int getValue()
    {
        return slider.getValue();
    }
}