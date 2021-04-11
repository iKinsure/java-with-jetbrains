package org.ikinsure.bot.bot;

/**
 * Bot behavior
 */
public interface Bot {

    void greetings();
    void guessAge();
    void counting();
    void quiz();
    void bye();

    default void run() {
        greetings();
        guessAge();
        counting();
        quiz();
        bye();
    }
}
