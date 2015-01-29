package ae.picarchive;

import java.util.List;

import org.skife.jdbi.v2.DBI;

import ae.picarchive.dao.UrlDAO;
import ae.picarchive.entity.Url;

public class ExportDownloadList {

    private static final String DOWNLOAD_DIR = "_downloads";

    public static void main(String[] args) {
        DBI dbi = new DBI("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
        UrlDAO dao = dbi.onDemand(UrlDAO.class);

        // dao.markUrlsAsWip(5);

        List<Url> result = dao.getWipUrls();
        for (Url u : result) {
            System.out.println("wget -O " + DOWNLOAD_DIR + "/" + u.getId() + " " + u.getUrl());
        }
    }
}
