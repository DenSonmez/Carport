package dat.backend.model.services;

import dat.backend.model.entities.CompleteProduct;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

// This class contains some methods to help with stuff related to the BudgetCalculator
public class DownloadHelper
{


    public static String convertItemListToCSV(List<CompleteProduct> products) {

        String csvReturnString = "Name,Length,Amount, Unit, Description\n";
        for (CompleteProduct item : products) {
            csvReturnString += item.getName() + "," + item.getLength() + "," + item.getAmount() + "," + item.getUnit() + "," + item.getDescription() + "\n";
        }
        return csvReturnString;
    }

    /**
     * This turns any string into an InputStream. It does this by getting the bytes of the string and uses a child class of InputStream, that turns a byte array into a InputStream.
     * This makes it possible to download "files" from memory
     * @param text The string to be turned to a InputStream
     * @return The InputStream from the 'text' parameter
     */
    public static InputStream stringToInputStream(String text){
        return new ByteArrayInputStream(text.getBytes());
    }
    public static InputStream bytesToInputStream(byte[] data){
        return new ByteArrayInputStream(data);
    }

    public static void sendDownloadCSVFileFromText(String csv, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=stykliste.csv");
        sendDownload(stringToInputStream(csv), response);
    }
    public static void sendDownloadSTLFileFromBytes(byte[] data, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=model.stl");
        sendDownload(bytesToInputStream(data), response);
    }
    private static void sendDownload(InputStream stream, HttpServletResponse response) throws IOException
    {
        try(InputStream in = stream;
            OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1048];
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }


}


