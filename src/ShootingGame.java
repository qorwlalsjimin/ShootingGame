import javax.swing.*;
import java.awt.*;

public class ShootingGame extends JFrame {
    //������۸��� �ʿ�
    private Image bufferImage;
    private Graphics screenGraphic;

    private Image mainScreen = new ImageIcon("src/images/main_screen.png").getImage();

    public ShootingGame(){
        //���� â ����
        setTitle("Shooting Game");
        setUndecorated(true);
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
    }

    //���� �̹��� ����� => ������ �ּ�ȭ
    public void paint(Graphics g){
        bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage, 0, 0, null);
    }

    //�ʿ��� ��� �׸���
    public void screenDraw(Graphics g){
        g.drawImage(mainScreen, 0,0,null);
        this.repaint();
    }
}
