package ae.picarchive.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import ae.picarchive.entity.Url;
import ae.picarchive.entity.UrlMapper;

public interface UrlDAO {

    @SqlUpdate("UPDATE urls SET status='WIP' WHERE id IN (SELECT id FROM urls WHERE status = 'NEW' LIMIT :limit)")
    void markUrlsAsWip(@Bind("limit") int limit);

    @SqlQuery("SELECT id, url, status FROM urls WHERE status = 'WIP'")
    @RegisterMapper(UrlMapper.class)
    List<Url> getWipUrls();

}