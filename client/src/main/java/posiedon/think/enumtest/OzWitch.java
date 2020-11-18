package posiedon.think.enumtest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/2 13:46
 */
public enum OzWitch {
    WEST("I am west"){
        void func(){
            System.out.println("west fun");
        }
    },
    EAST("I am east"){
        @Override
        void func() {
            System.out.println("east fun");
        }
    },
    NORTH("I am north"){
        @Override
        void func() {
            System.out.println("north fun");
        }
    },
    SOUTH("I am south"){
        @Override
        void func() {
            System.out.println("south fun");
        }
    }
    ;

    abstract void func();

    private String des;
    OzWitch(String des) {
        this.des=des;
    }

    public String getDes() {
        return des;
    }

    @Override
    public String toString() {
        String id=name();
        return id;
    }

    public static void main(String[] args) {
        for (OzWitch value : OzWitch.values()) {
            System.out.println(value+" : "+value.getDes());
            value.func();
        }
    }
}
