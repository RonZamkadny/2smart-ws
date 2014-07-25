package co.ronx;

public class User {
    private long id;

    public User(String id) {
        this.id = Long.valueOf(id);
    }

    public User(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
