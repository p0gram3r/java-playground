package org.p0gram3r.picarchive;

import static org.p0gram3r.picarchive.Configuration.DB_PASS;
import static org.p0gram3r.picarchive.Configuration.DB_URL;
import static org.p0gram3r.picarchive.Configuration.DB_USER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.p0gram3r.picarchive.dao.DAOFactory;
import org.p0gram3r.picarchive.dao.UrlDAO;

public class RegisterFile {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("usage: RegisterFile path/to/mapping-file");
        }

        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);
        UrlDAO urlDAO = daoFactory.getUrlDAO();

        File mappingFile = new File(args[0]);
        if (!mappingFile.exists()) {
            System.out.println("File not found: " + mappingFile.getCanonicalPath());
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(mappingFile)));
        System.out.println("reading mapping file: " + mappingFile.getCanonicalPath());

        String line;
        List<Long> urlIdList = new LinkedList<Long>();
        List<String> fileHashList = new LinkedList<String>();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length != 2) {
                System.out.println("unable to process mapping: '" + line + "'");
                continue;
            }

            urlIdList.add(Long.parseLong(parts[0]));
            fileHashList.add(parts[1]);

            if (urlIdList.size() >= 1000) {
                urlDAO.addFileHashToUrl(urlIdList, fileHashList);
                urlIdList.clear();
                fileHashList.clear();
            }
        }

        urlDAO.addFileHashToUrl(urlIdList, fileHashList);

        reader.close();
        System.out.println("finished mapping process");
    }
}
