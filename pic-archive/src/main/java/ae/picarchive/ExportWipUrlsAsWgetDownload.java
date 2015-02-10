package ae.picarchive;

import static ae.picarchive.Configuration.DB_PASS;
import static ae.picarchive.Configuration.DB_URL;
import static ae.picarchive.Configuration.DB_USER;

import java.util.List;

import ae.picarchive.dao.DAOFactory;
import ae.picarchive.dao.UrlDAO;
import ae.picarchive.entity.Url;

public class ExportWipUrlsAsWgetDownload {

    public static void main(String[] args) {
        String targetDir;
        if (args.length == 1) {
            targetDir = args[0];
        }
        else {
            throw new IllegalArgumentException("'targetDir' must be provided!");
        }

        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);
        UrlDAO dao = daoFactory.getUrlDAO();

        List<Url> result = dao.getWipUrls();
        for (Url u : result) {
            System.out.println("wget -qO " + targetDir + "/" + u.getId() + " " + u.getUrl());
            dao.markUrlAsDone(u);
        }
    }
}
