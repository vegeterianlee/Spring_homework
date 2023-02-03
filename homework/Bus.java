package homework;

public class Bus extends transportation{
    int maxPass = 30;		// 최대 승객 수
    int currentPass = 0;	// 현재 승객 수
    int cost = 1000;		// 요금
    // 버스 번호 지정 [고유값으로 생성되어야 되기에 랜덤함수로 함]
    public Bus(int count) {
        this.num = (int)(Math.random()*100+1); // num을 정의하지 않아도 부모클래스에서 가져옴
        // -> 1~100까지 번호로 지정
        this.count = count;
        System.out.println(count+ "번째 버스, 버스 번호 : "+num);
    }
    // 버스 상태 변경
    void busStatus(boolean change) {
        if(change == true)
            status = "운행중";
        else {
            status = "차고지행";
            currentPass = 0;
            maxPass = 30;
        }
    }
    // 버스 현재 상태
    void currentBus() {
        System.out.println("상태 = "+status);
        System.out.println("주유량 = "+currentGas);
        if(!gasLeft())
            System.out.println("주유 필요");
    }
    // 버스 기름에 따른 상태 변경
    void drive() {
        if(gasLeft()) {
            System.out.println("운행 가능");
        }
        if(!gasLeft()) {
            System.out.println("주유가 필요합니다");
            System.out.println("운행 불가 = 차고지행");
            status = "차고지행";
        }
    }

    // 주유량
    void refuel(int gas) {
        currentGas += gas;
        if(!gasLeft()) {
            status = "차고지행";
        }
    }


    boolean available() {
        //승객 탑승은 ‘최대 승객수’ 이하까지 가능
        return maxPass >= currentPass; //boolean이라 조건식 만족하면 true
    }

    // 승객 탑승
    int board(int pass) {
        if(pass >= (maxPass-currentPass))
            System.out.println("최대 승객 수 초과");
        else {
            if(available()) {
                currentPass += pass;
                System.out.println("탑승 승객 수 = "+pass+"명");
                System.out.println("잔여 승객 수 = "+(maxPass-currentPass)+"명");
                System.out.println("요금 확인 = "+(cost*pass));
            }
            if(!available()) {
                System.out.println("최대 승객 수 초과");
            }
        }
        return currentPass;

    }
    // 속도변경
    int changeSpeed(int acceleration) {
        //주유 상태를 체크하고 주유량이 10 이상이어야 운행할 수 있음
        if(gasLeft()) {
            this.acceleration = acceleration;

            currentSpeed += acceleration;

            System.out.println("현재 속도는 "+ currentSpeed+"입니다.");

        }
        System.out.println("주유량을 확인해주세요 "+currentGas);
        return currentGas;
    }
}
