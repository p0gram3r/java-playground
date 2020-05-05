package it.p0gram3r.playground;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Optional;
import java.util.stream.IntStream;

public class JdbiTestMain {

    public static void main(String[] args) {
        final Jdbi jdbi = connectToDatabase();

        initDatabaseLayout(jdbi);

        /////
        // entity mappers are used for converting ResultSet entries to Java objects
        /////

        // Option A: register a reflective mapper
        jdbi.registerRowMapper(ConstructorMapper.factory(Entry.class));
        // Option B: register a custom mapper
        // jdbi.registerRowMapper(new EntryMapper());

        /////
        // using Data Access Objects
        /////

        jdbi.installPlugin(new SqlObjectPlugin());

        final EntryDAO dao = jdbi.onDemand(EntryDAO.class);
        createEntries(dao, 1, 20);

        Entry e = dao.findById(1);
        System.out.println(e);

        /////
        // using plain SQL
        /////

        Optional<Entry> entry = selectEntryById(jdbi, 1);
        e = entry.orElseThrow(IllegalArgumentException::new);
        System.out.println(e);
    }


    private static Jdbi connectToDatabase() {
        String dbUrl = "jdbc:sqlite:jdbi/database/test-db.sqlite";
        String dbUser = "";
        String dbPass = "";
        return Jdbi.create(dbUrl, dbUser, dbPass);
    }

    private static void initDatabaseLayout(Jdbi dbi) {
        try (Handle h = dbi.open()) {
            h.execute("CREATE TABLE IF NOT EXISTS entry(id int PRIMARY KEY, key VARCHAR(100), value VARCHAR(100))");
        }
    }

    private static void createEntries(EntryDAO dao, int idRangeStart, int idRangeEnd) {
        IntStream.range(idRangeStart, idRangeEnd).forEach(id -> insertEntry(dao, id));
        System.out.println("done");
    }

    private static void insertEntry(EntryDAO dao, int id) {
        try {
            dao.insert(id, "key" + id, "value" + id);
        } catch (Exception ignored) {
        }
    }

    private static Optional<Entry> selectEntryById(Jdbi jdbi, int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("select id, key, value from entry where id = :id")
                        .bind("id", id)
                        .mapTo(Entry.class)
                        .findOne()
        );
    }

}
