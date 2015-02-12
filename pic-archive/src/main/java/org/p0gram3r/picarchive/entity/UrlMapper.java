package org.p0gram3r.picarchive.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UrlMapper implements ResultSetMapper<Url> {

    public Url map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Url url = new Url();
        url.setId(r.getLong("id"));
        url.setUrl(r.getString("url"));
        url.setStatus(r.getString("status"));

        return url;
    }
}
