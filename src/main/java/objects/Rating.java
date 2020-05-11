package objects;

/**
 * Rating class
 */
public class Rating{
    private String url;
    private String name;
    private int number;

    public Rating(String url, String name, int number) {
        this.url = url;
        this.name = name;
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int value) {
        this.number = value;
    }
}
