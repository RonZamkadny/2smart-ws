import co.ronx.MongoDBPropertySource;
import co.ronx.configuration.MongoDBConfig;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@ContextConfiguration(classes = { MongoDBConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class MongoDBPropertySourceTest {

    @Autowired
    private MongoTemplate mongo;

    @After
    public void after() {
        mongo.dropCollection(MongoDBPropertySource.Property.class);
    }

    @Test
    public void non_existing_property_should_be_null() {
        MongoDBPropertySource tested = new MongoDBPropertySource(mongo);
        assertNull(tested.getProperty("non_existing"));
    }

    @Test
    public void given_property_exists_when_get_then_it_should_be_returned() {
        String property = "someProperty";
        String value = "someValue";
        mongo.save(new MongoDBPropertySource.Property(property, value));
        MongoDBPropertySource tested = new MongoDBPropertySource(mongo);
        assertEquals(value, tested.getProperty(property));
    }

/*    @Test
    public void given_property_exists_when_property_is_removed_then_null_should_be_returned() throws Exception {
        String property = "someProperty";
        String value = "someValue";
        co.ronx.MongoDBPropertySource.Property propertyObject = new co.ronx.MongoDBPropertySource.Property(property, value);
        mongo.save(propertyObject);
        co.ronx.MongoDBPropertySource tested = new co.ronx.MongoDBPropertySource(mongo, 1L);
        Thread.sleep(10);
        mongo.remove(propertyObject);
        assertEquals(false, tested.containsProperty(property));
        assertEquals(null, tested.getProperty(property));
    }*/

/*    @Test
    public void given_property_exists_when_changed_then_it_should_be_updated_after_timeout() throws Exception {
        co.ronx.MongoDBPropertySource tested = new co.ronx.MongoDBPropertySource(mongo, 1L);

        String property = "someProperty";
        String value = "someValue";
        co.ronx.MongoDBPropertySource.Property propertyObject = new co.ronx.MongoDBPropertySource.Property(property, value);
        mongo.save(propertyObject);

        String updatedValue = "updatedValue";
        propertyObject.value = updatedValue;
        mongo.save(propertyObject);

        Thread.sleep(10);

        // containsProperty must alsways be run before getProperty (that's how Spring does it)
        assertTrue(tested.containsProperty(property));
        assertEquals(updatedValue, tested.getProperty(property));
    }*/

    @Document(collection="properties")
    static class Property {
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