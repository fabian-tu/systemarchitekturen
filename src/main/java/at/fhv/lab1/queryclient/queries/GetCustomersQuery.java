package at.fhv.lab1.queryclient.queries;

public class GetCustomersQuery {
    private String name;

    public GetCustomersQuery(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetCustomersQuery{" +
                "name='" + name +
                '}';
    }
}
