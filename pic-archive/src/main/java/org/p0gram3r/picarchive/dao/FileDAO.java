package org.p0gram3r.picarchive.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface FileDAO {

    @SqlUpdate("INSERT INTO files (path) values (:path)")
    void registerFile(@Bind("path") String path);

    @SqlQuery("SELECT id FROM files WHERE path = (:path)")
    Long getFileIdByPath(@Bind("path") String path);
}
