/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JasperReports;

import java.io.InputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author alber
 */
public class JReportVacaciones {
    
    public JasperReport getTodasLasVacaciones() {

        JasperReport report = null;
        InputStream input = getClass().getResourceAsStream("/reports/ReporteVacaciones.jrxml");

        if (input == null) {
            System.out.println("No se pudo cargar el archivo example.jrxml");
            return report;
        }

        try {
            report = JasperCompileManager.compileReport(input);
        } catch (JRException e) {
            e.printStackTrace();
        }

        return report;
    }
    
    
}
