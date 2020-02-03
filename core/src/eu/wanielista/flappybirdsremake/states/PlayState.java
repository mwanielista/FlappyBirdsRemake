package eu.wanielista.flappybirdsremake.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import eu.wanielista.flappybirdsremake.FlappyBirdsRemake;
import eu.wanielista.flappybirdsremake.sprites.Bird;
import eu.wanielista.flappybirdsremake.sprites.Font;
import eu.wanielista.flappybirdsremake.sprites.Scoring;
import eu.wanielista.flappybirdsremake.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -60;

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Tube tube;

    //scores
    private BitmapFont scoreFont;
    private Scoring scoring;
    public static int score_count;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyBirdsRemake.WIDTH / 2, FlappyBirdsRemake.HEIGHT / 2);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        scoring = new Scoring();
        Font font = new Font();
        scoreFont = font.getBitmap();
        font.setColor(Color.GOLD);
        font.setSize(Font.SIZE_BIG);

        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(tube.collides(bird.getBounds())){
                setscore(scoring.getScore());
                System.out.println("Sc: " +getScore());
                gameStateManager.set(new GameOverState(gameStateManager));
            }

            if(tube.score(bird.getBounds()) && ! tube.isScored()) {
                scoring.score();
                tube.markScored();
            }
        }


        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            setscore(scoring.getScore());
            System.out.println("Sc: " + getScore());
            gameStateManager.set(new GameOverState(gameStateManager));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y );
        for(Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        scoreFont.draw(sb, "Score: " + Integer.toString(scoring.getScore()), cam.position.x - cam.viewportWidth/2 + 10, 20);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        scoreFont.dispose();
        for(Tube tube: tubes){
            tube.dispose();
        }
        System.out.println("play state disposed");
        //place for adware
    }

    private void updateGround() {
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }

    }
    private void setscore(int sc)
    {
        score_count = sc; // Assigning a value;
    }

    public static int getScore()
    {
        return score_count;
    }

}
