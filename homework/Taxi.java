package homework;

public class Taxi extends transportation {
    String destination;            // 목적지
    int distance;                // 목적지까지 거리
    int maxPass = 4;            // 최대 승객수
    int defaultDistance = 1;    // 기본 거리
    int defaultCost = 3000;        // 기본 요금
    int perDistance = 1000;        // 거리당 요금
    static String status = "일반";        // 상태
    int total = 0;                // 누적 금액
    int cost;                    // 승객이 지불할 금액

    public Taxi(int count) {
        this.num = (int) (Math.random() * 100 + 1);
        this.count = count;
        System.out.println(count+ "번째 택시, 택시 번호 : "+num);
        Taxi.drive();
    }

    static boolean drive() { //택시 주유량 여부에 따라 상태 바꾸는 함수
        if (!gasLeft()) {
            status = "운행 불가";
            System.out.println("주유 필요");
            return false;
        }
        return true;
    }

    // 탑승	승객		목적지		거리
    void board(String dest, int dis) {
        if(status == "일반") {
                if(dis==1)
                    cost = defaultCost+ (perDistance*dis);
                else
                    cost = defaultCost+ (perDistance*(dis-1));
                status = "운행중";

                System.out.println("기본 요금 확인 = "+defaultCost);
                System.out.println("목적지 = "+dest);
                System.out.println("목적지까지 거리 = "+ dis+"km");
                System.out.println("지불할 요금 = "+cost);
                total += cost;


        }

    }

    // 주유량
    int refuel(int gas) {
        currentGas += gas;
        if(!gasLeft()) {
            status = "운행 불가";
        }
        return currentGas;

    }

    void pay() {
        status = "일반"; //결재 끝나고 다시 태울 수 있게끔 초기화
        maxPass = 4; //결재 끝나고 다시 태울 수 있게끔 초기화
        if(gasLeft()){
            System.out.println("누적 요금 = "+ total);
            cost = 0;
        }
        else if(!gasLeft()){
            System.out.println("누적 요금 = "+ total);
            System.out.println("주유 필요");
            cost = 0;
            status = "운행불가"; //결재 끝나고 다시 태울 수 있게끔 초기화
        }
    }

    void passenger(int pass) {
        System.out.println("탑승 승객 수 = "+pass);
        System.out.println("잔여 승객 수 = "+ (maxPass-pass));

    }
    // 속도변경
    int changeSpeed(int acceleration) {
        //주유 상태를 체크하고 주유량이 10 이상이어야 운행할 수 있음
        if(gasLeft()) {
            this.acceleration = acceleration;
            currentSpeed += acceleration;
            System.out.println("현재 속도는 "+ currentSpeed+"입니다.");
        }
        System.out.println("주유량을 확인해주세요."+currentGas);
        return currentGas;
    }
}