package org.p0gram3r.picarchive.dao;

import org.skife.jdbi.v2.DBI;

public class DAOFactory {

    private UrlDAO urlDao;
    private FileDAO fileDao;

    public DAOFactory(String url, String user, String pass) {
        DBI dbi = new DBI(url, user, pass);
        urlDao = dbi.onDemand(UrlDAO.class);
        fileDao = dbi.onDemand(FileDAO.class);
    }

    public UrlDAO getUrlDAO() {
        return urlDao;
    }

    public FileDAO getFileDAO() {
        return fileDao;
    }
}
