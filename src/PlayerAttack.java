import javax.swing.*;
import java.awt.*;

public class PlayerAttack {
    Image image = new ImageIcon("src/images/player_attack.png").getImage(); //���� �̹���
    int x,y; //��ġ
    int width = image.getWidth(null); //�̹��� �ʺ�
    int height = image.getHeight(null); //�̹��� ���� => �浹 ������ �ʿ�
    int attack = 5; //���ݷ�

    public PlayerAttack(int x, int y){
        this.x = x;
        this.y = y;
    }

    //�߻� �޼���
    public void fire(){
        this.x += 15; //������ ���������θ�
    }
}
