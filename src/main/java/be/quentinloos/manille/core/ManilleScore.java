package be.quentinloos.manille.core;

/**
 * A X-score Manille card game.
 *
 * @author Quentin Loos <contact@quentinloos.be>
 */
public class ManilleScore extends Manille {

    /** Score to get to finish the game */
    private int scoreLimit;

    public ManilleScore(int scoreLimit) {
        super();
        this.scoreLimit = scoreLimit;
    }

    @Override
    public boolean hasNext() {
        return getScores()[0] < scoreLimit && getScores()[1] < scoreLimit;
    }
}
