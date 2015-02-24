package org.p0gram3r.picarchive;

import static org.p0gram3r.picarchive.Configuration.DB_PASS;
import static org.p0gram3r.picarchive.Configuration.DB_URL;
import static org.p0gram3r.picarchive.Configuration.DB_USER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.p0gram3r.jbulkurl.entity.BulkUrl;
import org.p0gram3r.jbulkurl.parser.BaseReplacementParserFactory;
import org.p0gram3r.jbulkurl.parser.BulkUrlParser;
import org.p0gram3r.picarchive.dao.DAOFactory;
import org.p0gram3r.picarchive.dao.UrlDAO;
import org.p0gram3r.picarchive.util.BufferedUrlPersistenceHandler;

public class ImportBulkUrlFromFile {

    public static void main(String[] args) {
        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);
        UrlDAO dao = daoFactory.getUrlDAO();

        BulkUrlParser parser = new BulkUrlParser(new BaseReplacementParserFactory());
        BufferedUrlPersistenceHandler handler = new BufferedUrlPersistenceHandler(dao);
        for (String fileName : args) {
            asdF(fileName, parser, handler);
        }
        handler.flushBuffer();

        System.out.println();
        System.out.println("DONE!");
        System.out.println("  new: " + handler.getSuccessCount());
        System.out.println("  duplicates: " + handler.getDuplicateCount());
    }

    private static boolean asdF(String fileName, BulkUrlParser parser, BufferedUrlPersistenceHandler handler) {
        boolean success = true;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File(fileName)));

            String url;
            while ((url = in.readLine()) != null) {
                BulkUrl bulkUrl = parser.parse(url);
                bulkUrl.generateUrls(handler);

            }
        }
        catch (IOException e) {
            System.out.println("Error reading file '" + fileName + "'");
            e.printStackTrace(System.out);
            success = false;
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    System.out.println("Error closing file '" + fileName + "'");
                }
            }
        }
        return success;
    }
}
