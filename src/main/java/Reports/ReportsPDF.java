package Reports;

import Database.MySQLConnection;
import Database.Views.UserReportsDAO;
import Models.Views.UserReports;
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
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportsPDF {

    //UsuarioDAO
    //VistasDAO
    UserReportsDAO userReportsDAO = new UserReportsDAO(MySQLConnection.getConnection());


    public String DEST;

    public ReportsPDF(){
    }

    public static String timeStamp(){
        LocalDateTime aux = LocalDateTime.now();
        return LocalDate.now() + "_" + aux.getHour() + aux.getMinute() + aux.getSecond();
    }

    //updatePAth()
    public void updatePath(boolean type){
        if(type) DEST = "Reportes/ListaPosEst_"+ timeStamp() + ".pdf";
        else DEST = "Reportes/ListaPosPer_"+ timeStamp() + ".pdf";
    }

    public String ListaCasosEst() throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPDF(DEST , true);
        return DEST;
    }

    public String ListaCasosPer() throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPDF(DEST, false);
        return DEST;
    }

    private void createPDF(String dest, boolean type) throws IOException
    {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        Table table = new Table(UnitValue.createPercentArray(new float[]{5,5,5,5}))
                .useAllAvailableWidth();

        process(table, null, bold, true, type);
        if(type){
            for(UserReports user : userReportsDAO.getEstudiantes()){
                process(table, user, font, false, true);
            }
        } else {
            for(UserReports user : userReportsDAO.getPersonal()){
                process(table, user, font, false, false);
            }
        }
        document.add(new Paragraph("INSTITUTO TECNOLOGICO DE CELAYA").setFont(bold).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("LISTADO DE CASOS POSITIVOS COVID-19").setFont(bold).setTextAlignment(TextAlignment.CENTER));
        if(type){
            document.add(new Paragraph("ESTUDIANTES").setFont(bold).setTextAlignment(TextAlignment.CENTER));

        } else{
            document.add(new Paragraph("PERSONAL").setFont(bold).setTextAlignment(TextAlignment.CENTER));
        }
        document.add(table);
        //TODO opcional: agregar persona que crea el reporte
        document.add(new Paragraph("equipo Le Jose").setFont(font));

        //Close document
        document.close();
    }

    private void process(Table table, UserReports user, PdfFont font, boolean isHeader, boolean type) {
        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("NOMBRE").setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("FECHA DETECCION").setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("RESULTADO PRUEBA").setFont(font).setTextAlignment(TextAlignment.CENTER)));
            if(type){
                table.addHeaderCell(new Cell().add(new Paragraph("CARRERA").setFont(font).setTextAlignment(TextAlignment.CENTER)));
            } else{
                table.addHeaderCell(new Cell().add(new Paragraph("DEPARTAMENTO").setFont(font).setTextAlignment(TextAlignment.CENTER)));
            }
        } else {
            table.addCell(new Cell().add(new Paragraph(user.getNombre()).setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(user.getDetDate() + "").setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(user.getResult()).setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(user.getDepCarrera()).setFont(font).setTextAlignment(TextAlignment.CENTER)));
        }
    }
}
