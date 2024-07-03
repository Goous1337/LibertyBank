package api.utils;

import io.restassured.response.Response;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class PdfCompare {

    public static boolean isPdfEqualsIgnoringDates(String responsePdfText, String expectedPdfText) {
        String datePattern = "\\d{1,2} [а-яА-Я]+ \\d{4} года";
        String responsePdfWithoutDates = responsePdfText.replaceAll(datePattern, "");
        String expectedPdfWithoutDates = expectedPdfText.replaceAll(datePattern, "");
        return responsePdfWithoutDates.equals(expectedPdfWithoutDates);
    }

    public static boolean isPdfEqualsIgnoringDates(Response actualData, String expectedPdfFilePath) {
        try (InputStream responsePdfStream = actualData.asInputStream();
             InputStream expectedPdfStream = new FileInputStream(expectedPdfFilePath)) {

            String responsePdfText = getTextFromPdf(responsePdfStream);
            String expectedPdfText = getTextFromPdf(expectedPdfStream);

            return isPdfEqualsIgnoringDates(responsePdfText, expectedPdfText);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при сравнении текста из PDF", e);
        }
    }

    public static boolean isPdfEquals(byte[] responsePdf, File expectedPdf) {
        String responsePdfText = extractText(responsePdf);
        String expectedPdfText = extractText(expectedPdf);
        return responsePdfText.equals(expectedPdfText);
    }

    public static boolean isCommonPdfEquals(Response response, String expectedPdfFilePath) {
        byte[] responsePdf = response.asByteArray();
        File expectedPdf = new File(expectedPdfFilePath);
        return PdfCompare.isPdfEquals(responsePdf, expectedPdf);
    }

    private static String extractText(byte[] pdf) {
        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdf))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при извлечении текста из PDF", e);
        }
    }

    private static String extractText(File pdfFile) {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при извлечении текста из PDF", e);
        }
    }

    public static String getTextFromPdf(InputStream pdfStream) {
        try (PDDocument document = PDDocument.load(pdfStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при извлечении текста из PDF", e);
        }
    }
}