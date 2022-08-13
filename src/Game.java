import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class Game extends Thread { //���� ����
    private int delay = 20; //���� ������
    //������ ī��Ʈ => �̺�Ʈ �߻� �ֱ⸦ ��Ʈ��
    private long pretime;
    private int cnt;

    //�÷��̾� ���� ����
    private Image player = new ImageIcon("src/images/player.png").getImage();

    private int playerX, playerY;
    private int playerWidth = player.getWidth(null);
    private int playerHeight = player.getHeight(null);
    private int playerSpeed = 10; //Ű�Է��� �� �� �νĵ��� �� �÷��̾ �̵��� �Ÿ�
    private int playerUp = 30;

    private boolean up, down, left, right; //�÷��̾��� �������� ����
    private boolean shooting; //���� �߻� ����

    ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
    private PlayerAttack playerAttack;

    @Override
    public void run() {
        cnt = 0;
        playerX = 10;
        playerY = (Main.SCREEN_HEIGHT - playerHeight) / 2;

        while (true){
            //delay �и��ʰ� ���� ������ cnt ����
            pretime = System.currentTimeMillis();
            if(System.currentTimeMillis() - pretime < delay){ //���� �ð� - (cnt�� �����ϱ� �� �ð�) < delay
                //���̸�ŭ Thread�� sleep �ֱ�
                try{
                    Thread.sleep(delay - System.currentTimeMillis() + pretime);
                    keyProcess();
                    playerAttackProcess();
                    cnt++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Ű �Է� ó��
    private void keyProcess(){
        //playerX, playerY �� ����
        if(up && playerY - playerSpeed > 0) playerY -= playerSpeed;
        if(down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT) playerY += playerSpeed;
        if(left && playerX - playerSpeed > 0) playerX -= playerSpeed;
        if(right && playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH) playerX += playerSpeed;
        if(shooting && cnt % 15 == 0){
            playerAttack = new PlayerAttack(playerX+222, playerY+25);
            playerAttackList.add(playerAttack);
        }
    }

    //����
    private void  playerAttackProcess(){
        for(int i= 0; i<playerAttackList.size(); i++){
            playerAttack = playerAttackList.get(i);
            playerAttack.fire();
        }
    }

    //���� ���� ��� �׸���
    public void gameDraw(Graphics g){
        playerDraw(g);
    }

    //player�� ���� ��� �׸���
    public void playerDraw(Graphics g){
        g.drawImage(player, playerX, playerY, null);
        for(int i= 0; i<playerAttackList.size(); i++){
            playerAttack = playerAttackList.get(i);
            g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
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
