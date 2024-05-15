package entities;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conectorDB.ConectorDB;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entities.Employee;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Reportes {

 private String fechaActual = "";
    
  

    public void ReportesClientes() {
        Date date = new Date();
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);

        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Clientes.pdf"));

            Image header = Image.getInstance("C:/Users/USER/OneDrive/NetBeansProjectsPC/Capufe/CAPUFE/CAPUFE/CAPUFE/src/Images/Logo copia_1.png");
            header.setAlignment(Chunk.ALIGN_CENTER);

            // Fecha
            Paragraph fecha = new Paragraph();
            fecha.add(Chunk.NEWLINE); // Agregar nueva línea
            fecha.add("Fecha: " + fechaActual + "\n\n");

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.GREEN));
            parrafo.add("Reporte de Clientes \n\n");

            documento.open();

            // Agregar imagen y fecha al documento
            documento.add(header);
            documento.add(fecha);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Id");
            tabla.addCell("Name");
            tabla.addCell("Phone");
            tabla.addCell("Direction");
            tabla.addCell("User");

            try {
                Connection conexion = ConectorDB.get();
                PreparedStatement statement = conexion.prepareStatement("SELECT id, name, direction, phone, user FROM Customer;");
                ResultSet resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    do {
                        tabla.addCell(resultSet.getString(1));
                        tabla.addCell(resultSet.getString(2));
                        tabla.addCell(resultSet.getString(3));
                        tabla.addCell(resultSet.getString(4));
                        tabla.addCell(resultSet.getString(5));
                    } while (resultSet.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }


        public void ReportesEmpleados(){
       Date date = new Date();
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
    Document documento = new Document();
    try {
        String ruta = System.getProperty("user.home");
        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Empleados.pdf"));
       Image header = Image.getInstance("C:/Users/USER/OneDrive/NetBeansProjectsPC/Capufe/CAPUFE/CAPUFE/CAPUFE/src/Images/Logo copia_1.png");
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            
              // Fecha
            Paragraph fecha = new Paragraph();
            fecha.add(Chunk.NEWLINE); // Agregar nueva línea
            fecha.add("Fecha: " + fechaActual + "\n\n");
            
            
        //formato al texto
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.GREEN));
        parrafo.add("Reporte de Empleados \n\n");

        documento.open();
        // Agregar imagen y fecha al documento
            documento.add(header);
            documento.add(fecha);
            
        //agregamos los datos
        // documento.add(header);
        documento.add(parrafo);
          float[] columnsWidths = {3, 6, 7, 5, 4, 6};
        PdfPTable tabla = new PdfPTable(columnsWidths);
        tabla.addCell("Id");
        tabla.addCell("Name");
        tabla.addCell("Degree");
        tabla.addCell("Salary");
        tabla.addCell("Stand");
        tabla.addCell("User");
        

        try {
            Connection conexion = ConectorDB.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT e.id, e.name, e.degree, e.salary, s.name AS stand_name, e.user \n" +
"FROM capufe.employee AS e\n" +
"INNER JOIN capufe.stand AS s ON e.id_stand = s.id;;");
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                do {
                    tabla.addCell(resultSet.getString(1));
                    tabla.addCell(resultSet.getString(2));
                    tabla.addCell(resultSet.getString(3));
                    tabla.addCell(resultSet.getString(4));
                    tabla.addCell(resultSet.getString(5));
                    tabla.addCell(resultSet.getString(6));
                    
                } while (resultSet.next());
                documento.add(tabla);
            }

        } catch (SQLException e) {
            System.out.println("Error 4 en: " + e);
        }
        documento.close();

        JOptionPane.showMessageDialog(null, "Reporte creado");

    } catch (DocumentException e) {
        System.out.println("Error 1 en: " + e);
    } catch (FileNotFoundException ex) {
        System.out.println("Error 2 en: " + ex);
    } catch (IOException ex) {
        System.out.println("Error 3 en: " + ex);
    }
   
}
    
    }
