package org.p0gram3r.playground;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class JdbiTestMain {

    public static void main(String[] args) {
        DBI dbi = new DBI("jdbc:hsqldb:file:database/testdb", "SA", "");

        // ///
        // really low level
        // ///

        // Handle h = dbi.open();
        // initDatabaseLayout(h);
        // simpleSelect(h);
        // make sure to close it!
        // h.close();

        // ///
        // using simple DAO
        // ///

        PersonDAO dao = dbi.onDemand(PersonDAO.class);
        dao.insert(1337, "p0gram3r");
        assertThat(dao.findNameById(1337), equalTo("p0gram3r"));
    }

    static void initDatabaseLayout(Handle h) {
        h.execute("create table something (id int primary key, name varchar(100))");
        h.execute("insert into something (id, name) values (?, ?)", 3, "Patrick");
    }

    static void simpleSelect(Handle h) {
        List<Map<String, Object>> rs = h.select("select id, name from something");
        assertThat(rs.size(), equalTo(1));

        Map<String, Object> row = rs.get(0);
        assertThat((Integer) row.get("id"), equalTo(3));
        assertThat((String) row.get("name"), equalTo("Patrick"));
    }

}
