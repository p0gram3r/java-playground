package ae.picarchive;

import static ae.picarchive.Configuration.DB_PASS;
import static ae.picarchive.Configuration.DB_URL;
import static ae.picarchive.Configuration.DB_USER;

import java.sql.SQLIntegrityConstraintViolationException;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;

import ae.picarchive.dao.DAOFactory;
import ae.picarchive.dao.FileDAO;
import ae.picarchive.dao.UrlDAO;

public class RegisterFile {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("usage: RegisterFile (urlId) (filePath)");
        }

        long urlId = Long.parseLong(args[0]);
        String filePath = args[1];

        DAOFactory daoFactory = new DAOFactory(DB_URL, DB_USER, DB_PASS);

        FileDAO fileDAO = daoFactory.getFileDAO();
        Long fileId = registerFile(fileDAO, filePath);

        UrlDAO urlDAO = daoFactory.getUrlDAO();
        urlDAO.linkFileWithUrl(urlId, fileId);
    }

    private static Long registerFile(FileDAO fileDAO, String filePath) {
        try {
            fileDAO.registerFile(filePath);
        }
        catch (UnableToExecuteStatementException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                // ignore -> we tried to register the same file twice
            }
            else {
                throw e;
            }
        }

        return fileDAO.getFileIdByPath(filePath);
    }
}
