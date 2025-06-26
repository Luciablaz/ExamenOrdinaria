package es.ufv.dis.back.final2025.LBG;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class GeneradorPDF {

    public void generar(List<Usuario> usuarios) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("usuarios.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(3); // ID, Nombre, Email

            // Cabecera
            table.addCell("ID");
            table.addCell("Nombre");
            table.addCell("Email");

            // Datos
            for (Usuario u : usuarios) {
                table.addCell(u.getId().toString());
                table.addCell(u.getNombre());
                table.addCell(u.getEmail()); // ← Ya tienes este método en Usuario
            }

            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}