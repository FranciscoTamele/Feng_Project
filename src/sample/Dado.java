package sample;

public class Dado {

    public Dado() {
    }

    public Dado(String key, String token) {
        this.key = key;
        this.token = token;
    }

    private String key;
    private String token;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
