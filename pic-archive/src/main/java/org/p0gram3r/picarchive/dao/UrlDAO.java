package org.p0gram3r.picarchive.dao;

import java.util.List;

import org.p0gram3r.picarchive.entity.Url;
import org.p0gram3r.picarchive.entity.UrlMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

public interface UrlDAO {

    @SqlUpdate("INSERT INTO urls (url) values (:url)")
    void storeNewUrl(@Bind("url") String url);

    @SqlUpdate("UPDATE urls SET status = 'WIP' WHERE id IN (SELECT id FROM urls WHERE status = 'NEW' LIMIT :limit)")
    void markUrlsAsWip(@Bind("limit") int limit);

    @SqlQuery("SELECT id, url, status FROM urls WHERE status = 'WIP'")
    @RegisterMapper(UrlMapper.class)
    List<Url> getWipUrls();

    @SqlUpdate("UPDATE urls SET fileId = :fileId , status = 'DONE' WHERE id = :urlId")
    void linkFileWithUrl(@Bind("urlId") long urlId, @Bind("fileId") long fileId);
}
