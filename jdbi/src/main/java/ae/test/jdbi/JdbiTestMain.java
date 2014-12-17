package ae.test.jdbi;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class JdbiTestMain {

    public static void main(String[] args) {
        DBI dbi = new DBI("jdbc:hsqldb:file:database/testdb");
        Handle h = dbi.open();

        h.execute("create table something (id int primary key, name varchar(100))");
        h.execute("insert into something (id, name) values (?, ?)", 3, "Patrick");

        List<Map<String, Object>> rs = h.select("select id, name from something");
        assertThat(rs.size(), equalTo(1));

        Map<String, Object> row = rs.get(0);
        assertThat((Integer) row.get("id"), equalTo(3));
        assertThat((String) row.get("name"), equalTo("Patrick"));

        // make sure to close it!
        h.close();
    }

}
