package eu.wanielista.flappybirdsremake.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;

    protected State(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        cam = new OrthographicCamera();
        mouse = new Vector3();

    }

    protected abstract void handleInput();
    public abstract void update(float dt);  //delta time
    public abstract void render(SpriteBatch sb); //everything which we see on screen
    public abstract void dispose();

}
