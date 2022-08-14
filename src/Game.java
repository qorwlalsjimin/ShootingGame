import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    private boolean shooting; //공격 발사 유무

    private ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<EnemyAttack> enemyAttackList = new ArrayList<EnemyAttack>();
    private PlayerAttack playerAttack;
    private Enemy enemy;
    private EnemyAttack enemyAttack;

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
                    playerAttackProcess();
                    enemyAppearProcess();
                    enemyMoveProcess();
                    enemyAttackProcess();
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
        if(shooting && cnt % 15 == 0){
            playerAttack = new PlayerAttack(playerX+222, playerY+25);
            playerAttackList.add(playerAttack);
        }
    }

    //공격
    private void  playerAttackProcess(){
        for(int i= 0; i<playerAttackList.size(); i++){
            playerAttack = playerAttackList.get(i);
            playerAttack.fire();
        }
    }

    //주기적ㅈ으로 적 출현
    private  void enemyAppearProcess(){
        if(cnt % 80 == 0){
            enemy = new Enemy(1120, (int)(Math.random()*621));
            enemyList.add(enemy);
        }
    }

    //적 이동
    private void enemyMoveProcess(){
        for(int i = 0; i<enemyList.size(); i++){
            enemy = enemyList.get(i);
            enemy.move();
        }
    }

    private void enemyAttackProcess(){
        if(cnt % 50 == 0){
            enemyAttack = new EnemyAttack(enemy.x - 79, enemy.y + 35);
            enemyAttackList.add(enemyAttack);
        }

        //ArrayList에 담긴 공격 하나하나에 접근해 fire 메서드 호출
        for(int i = 0; i<enemyAttackList.size(); i++){
            enemyAttack = enemyAttackList.get(i);
            enemyAttack.fire();
        }
    }

    //게임 안의 요소 그리기
    public void gameDraw(Graphics g){
        playerDraw(g);
        enemyDraw(g);
    }

    //player에 관한 요소 그리기
    public void playerDraw(Graphics g){
        g.drawImage(player, playerX, playerY, null);
        for(int i= 0; i<playerAttackList.size(); i++){
            playerAttack = playerAttackList.get(i);
            g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
        }
    }

    //적과 적의 공격을 그려줄 메서드
    public void enemyDraw(Graphics g){
        //적
        for(int i = 0; i<enemyList.size(); i++){
            enemy = enemyList.get(i);
            g.drawImage(enemy.image, enemy.x, enemy.y, null);
        }
        //적의 공격
        for(int i = 0; i<enemyAttackList.size(); i++){
            enemyAttack = enemyAttackList.get(i);
            g.drawImage(enemyAttack.image, enemyAttack.x, enemyAttack.y, null);
        }
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

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
