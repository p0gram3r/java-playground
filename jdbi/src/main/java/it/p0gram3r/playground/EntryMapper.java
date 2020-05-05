package it.p0gram3r.playground;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryMapper implements RowMapper<Entry> {
    @Override
    public Entry map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Entry(
                rs.getInt("id"),
                rs.getString("key"),
                rs.getString("value")
        );
    }
}
