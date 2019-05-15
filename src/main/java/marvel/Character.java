package marvel;

import com.opencsv.bean.CsvBindByName;

/**
 * This is the Java representation of a Character
 */
public class Character {

    /**
     * name of the character
     */
    @CsvBindByName
    private String name;

    /**
     * book the character appears in
     */
    @CsvBindByName
    private String book;

    /**
     * get the name of the character
     *
     * @return name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the character
     *
     * @param name name of the character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the book of the character
     *
     * @return book of the character
     */
    public String getBook() {
        return this.book;
    }

    /**
     * set the book of the character
     *
     * @param book book of the character
     */
    public void setBook(String book) {
        this.book = book;
    }
}
