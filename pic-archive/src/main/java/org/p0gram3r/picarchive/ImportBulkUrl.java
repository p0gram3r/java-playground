package org.p0gram3r.picarchive;

import static org.p0gram3r.picarchive.Configuration.DB_PASS;
import static org.p0gram3r.picarchive.Configuration.DB_URL;
import static org.p0gram3r.picarchive.Configuration.DB_USER;

import java.sql.BatchUpdateException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;

import org.p0gram3r.jbulkurl.entity.BulkUrl;
import org.p0gram3r.jbulkurl.generator.GeneratedUrlHandler;
import org.p0gram3r.jbulkurl.parser.BaseReplacementParserFactory;
import org.p0gram3r.jbulkurl.parser.BulkUrlParser;
import org.p0gram3r.picarchive.dao.DAOFactory;
import org.p0gram3r.picarchive.dao.UrlDAO;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;

public class ImportBulkUrl {

    public static void main(String[] args) {
        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);
        UrlDAO dao = daoFactory.getUrlDAO();

        BulkUrlParser parser = new BulkUrlParser(new BaseReplacementParserFactory());
        StoreUrlInDatabaseHandler handler = new StoreUrlInDatabaseHandler(dao);
        for (String url : args) {
            BulkUrl bulkUrl = parser.parse(url);
            bulkUrl.generateUrls(handler);
        }
        handler.flushBuffer();

        System.out.println();
        System.out.println("DONE!");
        System.out.println("  new: " + handler.getSuccessCount());
        System.out.println("  duplicates: " + handler.getDuplicateCount());
    }
}

class StoreUrlInDatabaseHandler implements GeneratedUrlHandler {
    private long successCount = 0, duplicateCount = 0;
    private UrlDAO dao;
    private LinkedList<String> buffer;
    private int maxBufferSize;

    public StoreUrlInDatabaseHandler(UrlDAO dao) {
        this.dao = dao;
        this.buffer = new LinkedList<String>();
        this.maxBufferSize = 500;
    }

    public long getSuccessCount() {
        return successCount;
    }

    public long getDuplicateCount() {
        return duplicateCount;
    }

    @Override
    public void handle(String generatedUrl) {
        buffer.add(generatedUrl);

        if (buffer.size() >= maxBufferSize) {
            flushBuffer();
            System.out.println("new / duplicate urls : " + successCount + " / " + duplicateCount);
        }
    }

    public void flushBuffer() {
        if (buffer.isEmpty()) {
            return;
        }

        try {
            dao.storeListOfNewUrl(buffer);
            successCount += buffer.size();
        }
        catch (UnableToExecuteStatementException e) {
            if (e.getCause() instanceof BatchUpdateException) {
                sendEachEntrySeparatelyToDao();
            }
            else {
                throw e;
            }
        }
        buffer.clear();
    }

    private void sendEachEntrySeparatelyToDao() {
        for (String url : buffer) {
            try {
                dao.storeNewUrl(url);
                successCount++;
            }
            catch (UnableToExecuteStatementException e) {
                if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                    duplicateCount++;
                }
                else {
                    throw e;
                }
            }
        }
    }
}
