package eu.wanielista.flappybirdsremake.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int GRAVITY = -15;

    private int movement = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnim;

    Texture texture;
    private Sound flap;
    public float bird_x;

    public Bird(int x, int y) {

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnim = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    }

    public void update(float dt) {
        birdAnim.update(dt);
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);

        position.add(movement* dt, velocity.y, 0);
        bird_x = (movement * dt);   //bird x coord
        if(position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnim.getFrame();
    }

    public void jump() {
        velocity.y = 250;
        flap.play();
        flap.play(0.3f);

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }

    public void updateMovement() {
        movement += 10;
    }

}
