package co.ronx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertySource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;

public class MongoDBPropertySource extends PropertySource<Object> {
    private static final long DEFAULT_REFRESH_INTERVAL = 60000L;

    @Autowired
    private MongoTemplate mongoTemplate;

    public MongoDBPropertySource(MongoTemplate mongoTemplate) {
        super("mongoDB", DEFAULT_REFRESH_INTERVAL);
    }

    @Override
    public Object getProperty(String s) {
        return null;
    }

    @Document(collection="properties")
    public static class Property {
        @Id
        String name;
        Object value;
        public Property() {
        }
        public Property(String name, Object value) {
            this.name = name;
            this.value = value;
        }
    }
}
