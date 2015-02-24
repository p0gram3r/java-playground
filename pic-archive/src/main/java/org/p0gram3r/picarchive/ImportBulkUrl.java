package org.p0gram3r.picarchive;

import static org.p0gram3r.picarchive.Configuration.DB_PASS;
import static org.p0gram3r.picarchive.Configuration.DB_URL;
import static org.p0gram3r.picarchive.Configuration.DB_USER;

import org.p0gram3r.jbulkurl.entity.BulkUrl;
import org.p0gram3r.jbulkurl.parser.BaseReplacementParserFactory;
import org.p0gram3r.jbulkurl.parser.BulkUrlParser;
import org.p0gram3r.picarchive.dao.DAOFactory;
import org.p0gram3r.picarchive.dao.UrlDAO;
import org.p0gram3r.picarchive.util.BufferedUrlPersistenceHandler;

public class ImportBulkUrl {

    public static void main(String[] args) {
        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);
        UrlDAO dao = daoFactory.getUrlDAO();

        BulkUrlParser parser = new BulkUrlParser(new BaseReplacementParserFactory());
        BufferedUrlPersistenceHandler handler = new BufferedUrlPersistenceHandler(dao);
        for (String url : args) {
            BulkUrl bulkUrl = parser.parse(url);
            bulkUrl.generateUrls(handler);
        }
        handler.flushBuffer();

        System.out.println();
        System.out.println("DONE!");
        System.out.println("  new: " + handler.getNewCount());
        System.out.println("  duplicates: " + handler.getDuplicateCount());
    }
}
