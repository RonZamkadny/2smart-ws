package co.ronx.configuration;

import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@PropertySource("classpath:mongodb.properties")
@ComponentScan("co.ronx")
public class MongoDBConfig extends AbstractMongoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBConfig.class);

    @Autowired
    private Environment env;

    @Override
    public String getDatabaseName() {
        String db = env.getProperty("mongo.database");
        return db;
    }

    @Override
    public Mongo mongo() throws Exception {
        String host = env.getProperty("mongo.host");
        logger.debug("Using mongodb standalone host: " + host);
        return new Mongo(host);
    }
}
