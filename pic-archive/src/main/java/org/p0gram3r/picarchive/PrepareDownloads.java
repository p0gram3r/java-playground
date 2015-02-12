package org.p0gram3r.picarchive;

import static org.p0gram3r.picarchive.Configuration.DB_PASS;
import static org.p0gram3r.picarchive.Configuration.DB_URL;
import static org.p0gram3r.picarchive.Configuration.DB_USER;

import org.p0gram3r.picarchive.dao.DAOFactory;
import org.p0gram3r.picarchive.dao.UrlDAO;

public class PrepareDownloads {

    public static void main(String[] args) {
        int limit = 10;
        if (args.length == 1) {
            try {
                limit = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e) {
                // use default
            }
        }

        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);
        UrlDAO dao = daoFactory.getUrlDAO();

        System.out.println("Preparing " + limit + " urls for download...");
        dao.markUrlsAsWip(limit);
    }
}
