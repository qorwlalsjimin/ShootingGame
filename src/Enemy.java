import javax.swing.*;
import java.awt.*;

public class Enemy {
    Image image = new ImageIcon("src/images/enemy.png").getImage();
    int x, y;
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int hp = 10;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }

    //적 기체가 움직임
    public void move(){
        this.x -= 7;
    }
}
