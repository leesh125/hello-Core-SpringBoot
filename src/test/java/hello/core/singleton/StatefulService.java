package hello.core.singleton;

public class StatefulService {

    /*
        service에서 사용자가 공유하는 변수등을 수정하면
        실무에서 엄청난 문제가 생길 수 있다.(ex. 다른 사용자의 정보를 볼 수 있다.)

        해결책으로는 사용자가 공유하는 변수는 건드리지 못하게 한다.
        (이 클래스에선 들어오는 값을 바로 return해서 해결)
     */

    //private int price; // 상태를 유지하는 필드

    public int order(String name, int price){
        //this.price = price; // 여기가 문제
        System.out.println("name = "+ name + " price = " + price);
        return price;
    }

//    public int getPrice(){
//        return price;
//    }
}
