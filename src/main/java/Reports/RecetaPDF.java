package Reports;

import Database.MySQLConnection;
import Database.RecetaDAO;
import Models.Views.Receta.MedicamentoReceta;
import Models.Views.Receta.RecetaReporte;
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

public class RecetaPDF {

    RecetaDAO recetaDAO = new RecetaDAO(MySQLConnection.getConnection());
    String DEST;


    public void ReceiptGen(RecetaReporte receta) throws IOException {
        updatePath(receta);
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createReceipt(DEST, receta);
        //return DEST;
    }

    private void updatePath(RecetaReporte receta){
        DEST = "Reportes/Receta/Receta"+ receta.getNombreUser().substring(6)+receta.getFecha()+".pdf";
    }

    private void createReceipt(String dest, RecetaReporte receta) throws IOException {

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

        Table table = new Table(UnitValue.createPercentArray(new float[]{5,5})).useAllAvailableWidth();
        table.setStrokeWidth(0);

        process(table, null, bold, true);
        for(MedicamentoReceta med : recetaDAO.getMedsForPrescription(receta.getNoReceta())){
                process(table, med, font, false);
            }
        document.add(new Paragraph("INSTITUTO TECNOLOGICO DE CELAYA").setFont(bold).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("DEPARTAMENTO DE SERVICIOS ESCOLARES - SERVICIOS MEDICOS").setFont(bold).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("RECETA MEDICA").setFont(bold).setTextAlignment(TextAlignment.CENTER));

        Table table2 = new Table(UnitValue.createPercentArray(new float[]{5,5})).useAllAvailableWidth();
        table2.setStrokeWidth(0);
        table2.addHeaderCell(new Cell().add(new Paragraph("Medico: " +receta.getNombreMed()).setFont(font)));
        table2.addHeaderCell(new Cell().add(new Paragraph("Cedula Profesional: " + receta.getCedProf()).setFont(font)));
        document.add(table2);
        document.add(new Paragraph("FECHA: "+receta.getFecha()).setFont(bold).setTextAlignment(TextAlignment.LEFT));

        document.add(table);
        //TODO opcional: agregar persona que crea el reporte
        document.add(new Paragraph("equipo Le Jose").setFont(font));

        //Close document
        document.close();
    }

    private void process(Table table, MedicamentoReceta med, PdfFont font, boolean isHeader) {
        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("Medicamento").setFont(font).setTextAlignment(TextAlignment.LEFT)));
            table.addHeaderCell(new Cell().add(new Paragraph("Dosis").setFont(font).setTextAlignment(TextAlignment.LEFT)));
        } else {
            table.addCell(new Cell().add(new Paragraph(med.getNombre()).setFont(font).setTextAlignment(TextAlignment.LEFT)));
            table.addCell(new Cell().add(new Paragraph(med.getDosis()).setFont(font).setTextAlignment(TextAlignment.LEFT)));
        }
    }
}
