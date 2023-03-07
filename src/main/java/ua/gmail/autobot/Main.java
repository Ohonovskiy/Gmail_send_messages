package ua.gmail.autobot;

import ua.gmail.autobot.email.Email;
import ua.gmail.autobot.excel.Excel;

import java.util.Arrays;

public class Main {
    private final static String from = "yuriy.ohonovskiy@gmail.com";
    private final static String to = "";
    private final static String fileToSendPath = "src/main/resources/kp.docx";
    private final static String fileName = "Пропозиція.docx";
    private final static String subject = "Пропозиція про співпрацю";
    private final static String excelFilePath = "src/main/resources/test.xlsx";

    public static void main(String[] args) throws Exception {

        String[] emails = Excel.getValidEmailsFromExcel(excelFilePath);
        Email email = new Email();

        System.out.println(Arrays.toString(emails));

        for (String emailTo : emails){
            email.sendMail(from, emailTo, fileToSendPath, fileName, subject);
        }
    }
}
