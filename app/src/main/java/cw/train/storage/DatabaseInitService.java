package cw.train.storage;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public static void initDb(){
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway
            .configure()
            .dataSource(
                StorageConstants.CONNECTION_URL,
                null,
                null
            )
            .load();
        // Start the migration
        flyway.migrate();
    }
}
