package ae.picarchive.dao;

import org.skife.jdbi.v2.DBI;

public class DAOFactory {

    private UrlDAO urlDao;

    public DAOFactory(String url, String user, String pass) {
        DBI dbi = new DBI(url, user, pass);
        urlDao = dbi.onDemand(UrlDAO.class);
    }

    public UrlDAO getUrlDAO() {
        return urlDao;
    }
}
