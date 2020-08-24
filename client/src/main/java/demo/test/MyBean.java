package demo.test;

public class MyBean implements Cloneable{
    private String name;
    private Integer age;

    public MyBean() {
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public MyBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
