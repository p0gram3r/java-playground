package it.p0gram3r.playground;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface EntryDAO {

    @SqlUpdate("insert into entry (id, key, value) values (:id, :key, :value)")
    void insert(
            @Bind("id") int id,
            @Bind("key") String key,
            @Bind("value") String value
    );

    @SqlQuery("select id, key, value from entry where id = :id")
    Entry findById(@Bind("id") int id);
}
