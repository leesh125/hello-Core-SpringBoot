package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 static으로 선언하면 클래스 레벨에 올라가기 때문에 딱 하나만 생성됨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 외부에서 SingletonService를 생성하지 못하게(위에서 자기 자신을 하나만 생성함)
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
