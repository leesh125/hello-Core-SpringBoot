package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok 테스트
@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asda"); // lombok으로 자동 getter, setter 생성

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok); // ToString도 지원
    }
}
