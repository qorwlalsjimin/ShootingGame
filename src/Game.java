import javax.swing.*;
import java.awt.*;
import java.util.SplittableRandom;

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
                    keyProcess();
                    cnt++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //키 입력 처리
    private void keyProcess(){
        //playerX, playerY 값 조정
        if(up && playerY - playerSpeed > 0) playerY -= playerSpeed;
        if(down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT) playerY += playerSpeed;
        if(left && playerX - playerSpeed > 0) playerX -= playerSpeed;
        if(right && playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH) playerX += playerSpeed;
    }

    //게임 안의 요소 그리기
    public void gameDraw(Graphics g){
        playerDraw(g);
    }

    //player에 관한 요소 그리기
    public void playerDraw(Graphics g){
        g.drawImage(player, playerX, playerY, null);
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
