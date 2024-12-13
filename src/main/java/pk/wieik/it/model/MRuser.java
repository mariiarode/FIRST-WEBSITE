package pk.wieik.it.model;

public class MRuser {

    private String login = "";
    private int privileges = -1;
    // -1 user not logged in
    // 1 user logged in
    // 2 administrator

    private String name = "";
    private String surname = "";
    private int age = 0;
    private String password;

    private String color = "white";

    public MRuser(){}

    public MRuser(String login, String password, int privileges) {
        this.login = login;
        this.password = password;
        this.privileges = privileges;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPrivileges () {
        return privileges;
    }

    public void setPrivileges (int privileges) {
        this.privileges = privileges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getColor() {return this.color;}

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setColor(String color) {this.color=color;}

    @Override
    public String toString() {
        return "MRuser{" +
                "login='" + login + '\'' +
                ", privileges=" + privileges +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
