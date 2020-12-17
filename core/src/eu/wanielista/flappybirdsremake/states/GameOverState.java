package eu.wanielista.flappybirdsremake.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.wanielista.flappybirdsremake.FlyingBirds;
import eu.wanielista.flappybirdsremake.sprites.Font;

public class GameOverState extends State{

    private Texture background;
    private Texture playButton;
    private Texture gameOver;
    private Music gameOverSound;
    private BitmapFont scoreFont;
    private PlayState playState;

    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);
        cam.setToOrtho(false, FlyingBirds.WIDTH / 2, FlyingBirds.HEIGHT / 2);
        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
        playButton = new Texture("playbtn.png");
        gameOverSound = Gdx.audio.newMusic(Gdx.files.internal("game_over.mp3"));
        gameOverSound.setVolume(0.4f);
        gameOverSound.play();
        playState = new PlayState(gameStateManager);
        Font font = new Font();
        scoreFont = font.getBitmap();
        font.setColor(Color.GOLD);
        font.setSize(Font.SIZE_BIG);


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
        sb.draw(background, 0, 0);
        sb.draw(playButton, cam.position.x - playButton.getWidth() / 2, cam.position.y);
        sb.draw(gameOver, (cam.position.x - playButton.getWidth() / 2)-40, cam.position.y + 80);


        scoreFont.draw(sb, "Score: " + playState.getScore(), cam.position.x - cam.viewportWidth/2 + 75, 80);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("gameover state disposed");
    }

}
