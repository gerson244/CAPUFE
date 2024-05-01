/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.estilos;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author ricardorodriguez
 */
public class DegradedPanel extends JPanel {
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Define los colores y la posición del degradado
        Color color1 = new Color(237,240,228); // Rojo
        Color color2 = new Color(105,108,96); // Azul
        GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

        // Rellena el fondo del JPanel con el degradado
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
