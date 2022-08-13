import javax.swing.*;
import java.awt.*;

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
                    cnt++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
