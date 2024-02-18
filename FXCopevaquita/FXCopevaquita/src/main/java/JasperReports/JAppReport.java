package JasperReports;

import java.sql.Connection;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import javax.swing.*;
import java.util.Map;
import net.sf.jasperreports.swing.JRViewer;

public abstract class JAppReport {

    private static JasperReport jreport;
    private static JasperPrint jprint;

    public static void getReport(Connection conn, Map<String, Object> parameters, JasperReport report) {
        try {
            jreport = report;
            jprint = JasperFillManager.fillReport(jreport, parameters, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showReport() {
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);

        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setTitle("JasperViewerFX");
            stage.setScene(new Scene(new javafx.scene.layout.StackPane(swingNode), 800, 600));
            stage.show();
        });
    }

    private static void createSwingContent(SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            JRViewer viewer = new JRViewer(jprint);
            viewer.setOpaque(true);
            
            swingNode.setContent(viewer);
        });
    }
}
