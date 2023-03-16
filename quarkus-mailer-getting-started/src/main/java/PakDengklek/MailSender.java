package PakDengklek;

import io.quarkus.logging.Log;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.scheduler.Scheduled;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Path("report")
public class MailSender {
    @Inject
    Mailer mailer;
    public static Connection connection = null;
    @GET
   @Scheduled(cron="1 1 1 1 * ?")
     public void report() {
        Date date = new Date();
        Integer rowNUm = 1;
        String tanggal = LocalDate.now().toString()+"-"+LocalTime.now().getSecond()+LocalTime.now().getMinute()+LocalTime.now().getHour();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet"+tanggal);
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("UUID");
        header.createCell(1).setCellValue("Komoditas");
        header.createCell(2).setCellValue("total");
        header.createCell(3).setCellValue("CratedAt");
        header.createCell(4).setCellValue("UpdatedAt");
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_Pakdengklek", "postgres", "Nyayu123@");
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM nama_tabel");
            while (resultSet.next()){
                String uuid = resultSet.getString("id");
                String komoditas = resultSet.getString("komoditas");
                String total = resultSet.getString("total");
                String created = resultSet.getString("createAt");
                String updated = resultSet.getString("updateAt");
                Row dataRow = sheet.createRow(rowNUm);
                dataRow.createCell(0).setCellValue(uuid);
                dataRow.createCell(1).setCellValue(komoditas);
                dataRow.createCell(2).setCellValue(total);
                dataRow.createCell(3).setCellValue(created);
                dataRow.createCell(4).setCellValue(updated);
                rowNUm = rowNUm+1;}
            try {
                FileOutputStream out =  new FileOutputStream(new File("reportingTrack\\Laporan"+tanggal+".xlsx"));
                workbook.write(out);
                out.close();
                Log.info("Report has been generated successfully");
                mailer.send(Mail.withText("yogapermana1235k4@gmail.com",
                                "Mr. Dengklek's Harvest Reporting",
                                "Hello Mr. Dengklek.\n" +
                                        " I hope you are in good health.\n" +
                                        " This is the reporting of your garden results for this month.\n")
                       .addAttachment("Laporan hasil kebun"+ tanggal+".xlsx",
                                new File("reportingTrack\\Laporan"+tanggal+".xlsx"), "text/plain")
                           );
                Log.info("success");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.err.println(e);
        }
    }
}


