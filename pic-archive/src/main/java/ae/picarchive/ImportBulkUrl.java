package ae.picarchive;

import static ae.picarchive.Configuration.DB_PASS;
import static ae.picarchive.Configuration.DB_URL;
import static ae.picarchive.Configuration.DB_USER;

import java.sql.SQLIntegrityConstraintViolationException;

import org.p0gram3r.jbulkurl.entity.BulkUrl;
import org.p0gram3r.jbulkurl.generator.GeneratedUrlHandler;
import org.p0gram3r.jbulkurl.parser.BaseReplacementParserFactory;
import org.p0gram3r.jbulkurl.parser.BulkUrlParser;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;

import ae.picarchive.dao.DAOFactory;
import ae.picarchive.dao.UrlDAO;

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
        System.out.println();
        System.out.println("DONE!");
        System.out.println("  new: " + handler.getSuccessCount());
        System.out.println("  duplicates: " + handler.getDuplicateCount());
    }
}

class StoreUrlInDatabaseHandler implements GeneratedUrlHandler {
    private long successCount = 0, duplicateCount = 0;
    private UrlDAO dao;

    public StoreUrlInDatabaseHandler(UrlDAO dao) {
        this.dao = dao;
    }

    public long getSuccessCount() {
        return successCount;
    }

    public long getDuplicateCount() {
        return duplicateCount;
    }

    @Override
    public void handle(String generatedUrl) {
        try {
            dao.storeNewUrl(generatedUrl);
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

        if ((successCount + duplicateCount) % 100 == 0) {
            System.out.println("new / duplicate urls : " + successCount + " / " + duplicateCount);
        }
    }
}
