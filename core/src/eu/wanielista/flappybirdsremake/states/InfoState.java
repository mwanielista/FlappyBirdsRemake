package eu.wanielista.flappybirdsremake.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.wanielista.flappybirdsremake.FlyingBirds;
import eu.wanielista.flappybirdsremake.sprites.Font;
import eu.wanielista.flappybirdsremake.sprites.Scoring;

public class InfoState extends State{

    private Scoring scoring;
    private Texture background;
    private BitmapFont scoreFont;
    private BitmapFont maxScoreFont;



    public InfoState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.scoring = scoring;
        cam.setToOrtho(false, FlyingBirds.WIDTH/2, FlyingBirds.HEIGHT/2);
        background = new Texture("bg.png");

        Font font = new Font();
        scoreFont = font.getBitmap();
        font.setColor(Color.GOLD);
        font.setSize(Font.SIZE_BIG);
        maxScoreFont = font.getBitmap();

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
        String scoreText = "Score: " + Integer.toString(scoring.getScore());
        scoreFont.draw(sb, scoreText, cam.position.x - getStringWidth(scoreFont, scoreText)/2, cam.position.y);


        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        scoring.dispose();
        scoreFont.dispose();
        maxScoreFont.dispose();
        System.out.println("Info State Disposed");
    }

    private float getStringWidth(BitmapFont font, String str) {
        GlyphLayout glyphLayout = new GlyphLayout(font, str);
        return glyphLayout.width;
    }
}
