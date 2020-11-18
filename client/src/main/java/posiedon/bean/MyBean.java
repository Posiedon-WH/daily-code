package posiedon.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("age")
public class MyBean {

    private String name;
    private int age;

    @JsonIgnore
    private int income;

    public MyBean() {
    }

    public MyBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public MyBean(String name, int age, int income) {
        this.name = name;
        this.age = age;
        this.income = income;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", income=" + income +
                '}';
    }
}
