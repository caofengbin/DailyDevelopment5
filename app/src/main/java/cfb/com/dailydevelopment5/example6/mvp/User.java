package cfb.com.dailydevelopment5.example6.mvp;

/**
 * Model层的具体实现类
 * Created by fengbincao on 2018/3/18.
 */

public class User {

    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
