package eu.wanielista.flappybirdsremake.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Scoring {

    private Sound scoreSound;
    private int score;
    private int step = 1;
    private Pref pref;

    private int acceleration = 1;


    public Scoring() {
        scoreSound = Gdx.audio.newSound(Gdx.files.internal("score.wav"));
        pref = new Pref();

        acceleration += 2;
    }

    public void score() {
        scoreSound.play(0.3f);
        score += step;

    }


    public int getScore() {
        return score;
    }

    public void dispose() {
        scoreSound.dispose();
    }

    public void setStep(int step) {
        this.step = step;
    }


}
