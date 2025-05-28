package forcasolidaria.Infrastructure;

import io.github.cdimascio.dotenv.Dotenv;
import io.quarkus.logging.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    static Dotenv dotenv = Dotenv.configure()
            .directory("./")
            .filename("enviromentfile.env")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    public static final String USER = dotenv.get("DATABASE_USER");
    public static final String PASSWORD = dotenv.get("DATABASE_PASSWORD");
    public static final String URL = dotenv.get("DATABASE_URL");

    public static Connection getConnection() throws SQLException{
        Log.info("database user: " + dotenv.get("DATABASE_USER"));
        Log.info("database password: " + dotenv.get("DATABASE_PASSWORD"));
        Log.info("database url: " + dotenv.get("DATABASE_URL"));
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
