import javax.swing.*;
import java.awt.*;

public class Game extends Thread { //게임 진행
    private int delay = 20; //게임 딜레이
    //딜레이 카운트 => 이벤트 발생 주기를 컨트롤
    private long pretime;
    private int cnt;

    //플레이어 관련 변수
    private Image player = new ImageIcon("src/images/player.png").getImage();

    private int playerX, playerY;
    private int playerWidth = player.getWidth(null);
    private int playerHeight = player.getHeight(null);
    private int playerSpeed = 10; //키입력이 한 번 인식됐을 때 플레이어가 이동할 거리
    private int playerUp = 30;

    private boolean up, down, left, right; //플레이어의 움직임을 제어

    @Override
    public void run() {
        cnt = 0;
        playerX = 10;
        playerY = (Main.SCREEN_HEIGHT - playerHeight) / 2;

        while (true){
            //delay 밀리초가 지날 때마다 cnt 증가
            pretime = System.currentTimeMillis();
            if(System.currentTimeMillis() - pretime < delay){ //현재 시간 - (cnt가 증가하기 전 시간) < delay
                //차이만큼 Thread에 sleep 주기
                try{
                    Thread.sleep(delay - System.currentTimeMillis() + pretime);
                    cnt++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
