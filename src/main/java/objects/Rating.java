package objects;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return number == rating.number &&
                url.equals(rating.url) &&
                name.equals(rating.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name, number);
    }
}
