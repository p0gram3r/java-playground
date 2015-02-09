package ae.picarchive.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import ae.picarchive.entity.Url;
import ae.picarchive.entity.UrlMapper;

public interface UrlDAO {

    @SqlUpdate("INSERT INTO urls (url) values (:url)")
    void storeNewUrl(@Bind("url") String url);

    @SqlUpdate("UPDATE urls SET status='WIP' WHERE id IN (SELECT id FROM urls WHERE status = 'NEW' LIMIT :limit)")
    void markUrlsAsWip(@Bind("limit") int limit);

    @SqlQuery("SELECT id, url, status FROM urls WHERE status = 'WIP'")
    @RegisterMapper(UrlMapper.class)
    List<Url> getWipUrls();

    @SqlUpdate("UPDATE urls SET status='DONE' WHERE id = :id")
    void markUrlAsDone(@Bind("id") long id);
}
