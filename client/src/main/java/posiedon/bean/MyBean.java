package posiedon.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties("age")
@Data
public class MyBean {

    private String name;
    private Integer age;

    private String content;
    private Long money;

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

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //    @Override
//    public String toString() {
//        return "MyBean{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", income=" + income +
//                '}';
//    }
}
