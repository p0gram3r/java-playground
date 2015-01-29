package ae.picarchive;

import static ae.picarchive.Configuration.DB_PASS;
import static ae.picarchive.Configuration.DB_URL;
import static ae.picarchive.Configuration.DB_USER;
import static ae.picarchive.Configuration.DIR_DOWNLOAD;

import java.util.List;

import ae.picarchive.dao.DAOFactory;
import ae.picarchive.dao.UrlDAO;
import ae.picarchive.entity.Url;

public class ExportWipUrlsAsWgetDownload {

    public static void main(String[] args) {
        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);
        UrlDAO dao = daoFactory.getUrlDAO();

        List<Url> result = dao.getWipUrls();
        for (Url u : result) {
            System.out.println("wget -qO " + DIR_DOWNLOAD + "/" + u.getId() + " " + u.getUrl());
        }
    }
}
