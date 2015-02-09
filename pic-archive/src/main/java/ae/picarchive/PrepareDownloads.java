package ae.picarchive;

import static ae.picarchive.Configuration.DB_PASS;
import static ae.picarchive.Configuration.DB_URL;
import static ae.picarchive.Configuration.DB_USER;
import ae.picarchive.dao.DAOFactory;
import ae.picarchive.dao.UrlDAO;

public class PrepareDownloads {

    public static void main(String[] args) {
        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);
        UrlDAO dao = daoFactory.getUrlDAO();

        int limit = 10;
        if (args.length == 1) {
            try {
                limit = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e) {
                // use default
            }
        }

        System.out.println("Preparing " + limit + " urls for download...");
        dao.markUrlsAsWip(limit);
    }
}
