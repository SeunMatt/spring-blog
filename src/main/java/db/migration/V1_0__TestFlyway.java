package db.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by smatt on 29/06/2017.
 */
public class V1_0__TestFlyway implements SpringJdbcMigration {

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        String insertData = "INSERT INTO users (nmae, email) VALUES('SmattFLYWAY', 'smatt@gmail.com')";
        jdbcTemplate.execute(insertData);
    }

}
