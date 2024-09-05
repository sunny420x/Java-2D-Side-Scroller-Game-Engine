package Projects.Java_2D_Engine;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class ShapeDrawing extends JPanel {
    // Textures
    Image wall_texture;
    Image floor_texture;
    public static Integer wall_texture_size = 128;
    public static Integer floor_texture_size = 128;

    public ShapeDrawing() {
        loadWallTexture("/Users/costerraid/Java/Projects/Java_2D_Engine/textures/s2 Painted Wall Yellow 01.png");
        loadFloorTexture("/Users/costerraid/Java/Projects/Java_2D_Engine/textures/s2 Tiles Rect Orange.png");
    }

    public static int posX = 550;
    public static int posY = 200;

    public static int MapObj[][] = {
        {0,0}, {wall_texture_size*1,0}, {wall_texture_size*2,0}, {wall_texture_size*3,0}, {wall_texture_size*4,0}, {wall_texture_size*5, 0}, {wall_texture_size*6, 0}, {wall_texture_size*7, 0}
    };
    public static int FloorObj[][] = {
        {0,0}, {wall_texture_size*1,0}, {wall_texture_size*2,0}, {wall_texture_size*3,0}, {wall_texture_size*4,0}, {wall_texture_size*5, 0}, {wall_texture_size*6, 0}, {wall_texture_size*7, 0}
    };
    public static int playerPosX = 600;
    public static int playerPosY = 200;

    public static int absolute_playerPosX = 600 - posX;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Graphics2D g2d = (Graphics2D) g;

        absolute_playerPosX = 600 - posX;
        System.out.println("Player PosX: "+absolute_playerPosX);

        g.setColor(Color.black);
        g.fillRect(0, 0, 1280, 720);
        
        //Draw map Here!
        for(int i = 0; i < MapObj.length; i++) {
            if(wall_texture != null) {
                g.drawImage(wall_texture, MapObj[i][0]+posX, MapObj[i][1]+posY, getFocusCycleRootAncestor());
                g.drawImage(wall_texture, MapObj[i][0]+posX, MapObj[i][1]+posY-wall_texture_size, getFocusCycleRootAncestor());
            } else {
                g.setColor(Color.pink);
                g.fillRect(MapObj[i][0]+posX, MapObj[i][1]+posY, 250, 300);
            }
            if(floor_texture != null) {
                g.drawImage(floor_texture, FloorObj[i][0]+posX, FloorObj[i][1]+posY+wall_texture_size, getFocusCycleRootAncestor());
                // AffineTransform transform = new AffineTransform();
                // transform.translate(FloorObj[i][0] + posX, FloorObj[i][1] + posY + wall_texture_size);
                // g2d.setTransform(transform);
                // g2d.drawImage(floor_texture, FloorObj[i][0]+posX, FloorObj[i][1]+posY+wall_texture_size, getFocusCycleRootAncestor());

            } else {
                g.setColor(Color.blue);
                g.fillRect(FloorObj[i][0]+posX, FloorObj[i][1]+posY+wall_texture_size, 256, 128);
            }
        }

        //Draw Player Here!
        g.setColor(Color.green);
        g.fillRect(playerPosX, playerPosY, 100, 200);
    }

    public void update() {

    }

    public void loadWallTexture(String path) {
        File file = new File(path);
        try {
            wall_texture = ImageIO.read(file);
        } catch(IOException e) {
            e.printStackTrace();
            wall_texture = null;
        }
    }
    public void loadFloorTexture(String path) {
        File file = new File(path);
        try {
            floor_texture = ImageIO.read(file);
        } catch(IOException e) {
            e.printStackTrace();
            floor_texture = null;
        }
    }
}