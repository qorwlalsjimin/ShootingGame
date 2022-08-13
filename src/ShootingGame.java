import javax.swing.*;
import java.awt.*;

public class ShootingGame extends JFrame {
    //더블버퍼링에 필요
    private Image bufferImage;
    private Graphics screenGraphic;

    private Image mainScreen = new ImageIcon("src/images/main_screen.png").getImage();

    public ShootingGame(){
        //게임 창 설정
        setTitle("Shooting Game");
        setUndecorated(true);
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
    }

    //버퍼 이미지 만들기 => 깜빡임 최소화
    public void paint(Graphics g){
        bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage, 0, 0, null);
    }

    //필요한 요소 그리기
    public void screenDraw(Graphics g){
        g.drawImage(mainScreen, 0,0,null);
        this.repaint();
    }
}
