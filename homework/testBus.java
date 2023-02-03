package homework;

public class testBus {
    public static void main(String[] args) {
        // 버스 테스트
        // 1번
        // 1~2. 버스 2대 생성 & 출력
        Bus bus1 = new Bus(1); //번째와 버스넘버가 보임
        Bus bus2 = new Bus(2);

        // 2번 (버스 1대로 진행)
        // 1 ~ 2. 승객 +2 & 출력
        bus1.board(2);
        // 3 ~ 4. 주유량 -50
        bus1.refuel(-50);
        // void refuel 함수안에 sout을 적어도 되지만,
        // 그럴경우 주유량 60이 상태 = 차고지행 위에 결과값으로 뜬다
        System.out.println("주유량 = "+bus1.currentGas);
        // 5. 상태 변경 => 차고지행
        bus1.busStatus(false);
        // 6. 주유량 +10
        bus1.refuel(10);
        // 7. 버스 상태와 주유량 출력
        bus1.currentBus();
        // 8. 상태 변경 => 운행중
        bus1.busStatus(true);
        // 9 ~ 10. 승객 +45 => 최대 승객 수 초과
        bus1.board(45);
        // 11 ~ 12. 승객 +5 & 출력
        bus1.board(5);
        // 13. 주유량 -55
        bus1.refuel(-55);
        // 14. 버스 상태와 주유량 출력
        bus1.currentBus();
        // 15. 주유량 +55
        bus1.refuel(55);
        // 15. 버스 속도 변경
        bus1.changeSpeed(30);

    }

}

