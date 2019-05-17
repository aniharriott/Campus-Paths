package marvel;

import com.opencsv.bean.CsvBindByName;

/**
 * This is the Java representation of a MarvelBean
 */
public class MarvelBean {

    /**
     * name of the hero
     */
    @CsvBindByName
    private String hero;

    /**
     * book the hero appears in
     */
    @CsvBindByName
    private String book;

    /** boolean value for debugging */
    private boolean DEBUG = false;

    // Abstraction Function:
    //  hero = the hero's name
    //  book = the book the hero appears in
    // Rep Invariant:
    //  hero != null
    //  book != null

    /**
     * get the name of the hero
     *
     * @return name of the hero
     */
    public String getHero() {
        return hero;
    }

    /**
     * set the name of the hero
     *
     * @param hero name of the hero
     */
    public void setHero(String hero) {
        this.hero = hero;
        checkRep();
    }

    /**
     * get the book of the hero
     *
     * @return book of the hero
     */
    public String getBook() {
        return this.book;
    }

    /**
     * set the book of the hero
     *
     * @param book book of the hero
     */
    public void setBook(String book) {
        this.book = book;
        checkRep();
    }

    /** Checks if the rep invariant is violated **/
    private void checkRep() {
        if (DEBUG) {
            assert hero != null;
            assert book != null;
        }
    }
}
