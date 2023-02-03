package homework;

public class transportation {
    //공통적인 번호, 주유량, 현재 속도, 가속, 최대승객수, 상태만
    int num;			// 번호
    int count; // 차량 순서
    static int currentGas = 100;		// 주유량
    int currentSpeed = 0;	// 현재 속도
    int acceleration;	// 가속
    int maxPass;		// 최대 승객 수
    String status = "";	// 상태

    // 주유상태 확인 후 운행가능성 확인하는 함수까지
    static boolean gasLeft() {
        //주유량이 10 이상이어야 운행
        return currentGas >= 10; //10보다 크거나 같다면 true, 작다면 false
    }
}
