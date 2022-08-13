import javax.swing.*;
import java.awt.*;

public class PlayerAttack {
    Image image = new ImageIcon("src/images/player_attack.png").getImage(); //공격 이미지
    int x,y; //위치
    int width = image.getWidth(null); //이미지 너비
    int height = image.getHeight(null); //이미지 높이 => 충돌 판정에 필요
    int attack = 5; //공격력

    public PlayerAttack(int x, int y){
        this.x = x;
        this.y = y;
    }

    //발사 메서드
    public void fire(){
        this.x += 15; //공격은 오른쪽으로만
    }
}
