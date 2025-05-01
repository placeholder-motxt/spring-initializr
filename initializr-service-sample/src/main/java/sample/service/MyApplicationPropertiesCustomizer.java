package sample.service;

import io.spring.initializr.generator.spring.properties.ApplicationProperties;
import io.spring.initializr.generator.spring.properties.ApplicationPropertiesCustomizer;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationPropertiesCustomizer implements ApplicationPropertiesCustomizer {

    @Override
    public void customize(ApplicationProperties properties) {
        properties.add("springdoc.api-docs.enabled", "true");
        properties.add("springdoc.swagger-ui.enabled", "true");
        properties.add("spring.datasource.url", "jdbc:sqlite:mydatabase.db");
        properties.add("spring.datasource.driver-class-name", "org.sqlite.JDBC");
        properties.add("spring.jpa.show-sql", "true");
        properties.add("spring.jpa.database-platform", "org.hibernate.community.dialect.SQLiteDialect");
        properties.add("spring.jpa.hibernate.ddl-auto", "update");
    }
}
