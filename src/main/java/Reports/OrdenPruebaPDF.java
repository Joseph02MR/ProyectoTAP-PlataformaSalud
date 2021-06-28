package Reports;

import Database.MySQLConnection;
import Database.OrdenPruebaDAO;
import Models.Views.Receta.OrdenReporte;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;
import java.io.IOException;

public class OrdenPruebaPDF {

    OrdenPruebaDAO ordenPruebaDAO = new OrdenPruebaDAO(MySQLConnection.getConnection());
    String DEST;


    public String ordenReporteGen(OrdenReporte orden) throws IOException {
        updatePath(orden);
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createReceipt(DEST, orden);
        return DEST;
    }

    private void updatePath(OrdenReporte orden){
        DEST = "Reportes/Ordenes/OrdenP"+ orden.getPaciente().substring(6)+orden.getFechaExp()+".pdf";
    }

    private void createReceipt(String dest, OrdenReporte orden) throws IOException {

        //Initialize PDF writear
        PdfWriter writer = new PdfWriter(dest);
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        // Initialize document
        PageSize ps = PageSize.A5.rotate();
        Document document = new Document(pdf, ps);
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        Table table = new Table(UnitValue.createPercentArray(new float[]{5})).useAllAvailableWidth();

        document.add(new Paragraph("INSTITUTO TECNOLOGICO DE CELAYA").setFont(bold).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("DEPARTAMENTO DE SERVICIOS ESCOLARES - SERVICIOS MEDICOS").setFont(bold).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("ORDEN DE PRUEBA COVID-19").setFont(bold).setTextAlignment(TextAlignment.CENTER));
        for (int w = 0; w < 4; w++) {
            document.add(new Paragraph(""));
        }

        Table table2 = new Table(UnitValue.createPercentArray(new float[]{5,5})).useAllAvailableWidth();
        table2.addHeaderCell(new Cell().add(new Paragraph("NO. ORDEN: " +orden.getNoReporte()).setFont(font)));
        table2.addHeaderCell(new Cell().add(new Paragraph("FECHA: " + orden.getFechaExp()).setFont(font)));

        table.addCell(new Cell().add(new Paragraph("PACIENTE: " +orden.getPaciente())).setFont(font));
        table.addCell(new Cell().add(new Paragraph("TIPO DE PRUEBA: " +orden.getTipoPrueba()).setFont(font)));
        table.addCell(new Cell().add(new Paragraph("RESULTADO: " +orden.getResultado()).setFont(font)));
        document.add(table2);
        for (int w = 0; w < 5; w++) {
            document.add(new Paragraph(""));
        }
        document.add(table);
        document.add(new Paragraph("equipo Le Jose").setFont(font));

        //Close document
        document.close();
    }
}
