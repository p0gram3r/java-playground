package org.p0gram3r.picarchive;

import static org.p0gram3r.picarchive.Configuration.DB_PASS;
import static org.p0gram3r.picarchive.Configuration.DB_URL;
import static org.p0gram3r.picarchive.Configuration.DB_USER;

import java.util.List;

import org.p0gram3r.picarchive.dao.DAOFactory;
import org.p0gram3r.picarchive.dao.UrlDAO;
import org.p0gram3r.picarchive.entity.Url;

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
        }
    }
}
