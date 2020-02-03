package eu.wanielista.flappybirdsremake.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    public static final int TUBE_WIDTH = 52;

    private Texture topTube;
    private Texture bottomTube;

    private static final int FLUCTUATION = 130;
    private static final int LOWEST_OPENING = 120; //lowest open for bottom tube
    private static final int TUBE_GAP = 100; //differents between 2 opened cubes, space for bird

    private Vector2 positionTopTube, positionBottomTube;
    private Rectangle boundsTop, boundsBottom, boundsIn;
    private Random rand;

    private boolean isScored;


    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        positionTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop =  new Rectangle(positionTopTube.x, positionTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(positionBottomTube.x, positionBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
        boundsIn = new Rectangle(positionBottomTube.x + topTube.getWidth(), positionBottomTube.y + bottomTube.getHeight(), 50, TUBE_GAP);
        isScored = false;
    }


    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }
    public void reposition(float x){
        positionTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube.set(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(positionTopTube.x, positionTopTube.y);
        boundsBottom.setPosition(positionBottomTube.x, positionBottomTube.y);
        boundsIn.setPosition(x + topTube.getWidth(), positionBottomTube.y + boundsBottom.getHeight());
        isScored = false;
    }

    public boolean score(Rectangle player) {
        return player.overlaps(boundsIn);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public void markScored() {
        isScored = true;
    }

    public boolean isScored() {
        return isScored;
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
