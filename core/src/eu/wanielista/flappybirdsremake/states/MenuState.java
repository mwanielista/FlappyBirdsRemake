package eu.wanielista.flappybirdsremake.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.wanielista.flappybirdsremake.FlappyBirdsRemake;

public class MenuState extends State{

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        cam.setToOrtho(false, FlappyBirdsRemake.WIDTH / 2, FlappyBirdsRemake.HEIGHT / 2);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
//        sb.draw(background, 0, 0, FlappyBirdsRemake.WIDTH, FlappyBirdsRemake.HEIGHT);
        sb.draw(background, 0, 0);
//        sb.draw(playButton, (FlappyBirdsRemake.WIDTH / 2) - (playButton.getWidth() / 2), FlappyBirdsRemake.HEIGHT / 2);
        sb.draw(playButton, cam.position.x - playButton.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("menu state disposed");
    }
}
