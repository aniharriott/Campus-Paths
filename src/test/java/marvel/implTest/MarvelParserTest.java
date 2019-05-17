package marvel.implTest;

import marvel.MarvelParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public final class MarvelParserTest {

    /** tests that MarvelParser works correctly */
    @Test
    public void testMarvelParser() {
        Map<String, List<String>> expected = new HashMap<String, List<String>>();
        List<String> for331 = new LinkedList<String>();
        for331.add("Ernst-the-Bicycling-Wizard");
        for331.add("Notkin-of-the-Superhuman-Beard");
        for331.add("Perkins-the-Magical-Singing-Instructor");
        for331.add("Grossman-the-Youngest-of-them-all");
        List<String> for403 = new LinkedList<>();
        for403.add("Ernst-the-Bicycling-Wizard");
        for403.add("Notkin-of-the-Superhuman-Beard");
        List<String> for401 = new LinkedList<String>();
        for401.add("Perkins-the-Magical-Singing-Instructor");
        List<String> for332 = new LinkedList<String>();
        for332.add("Grossman-the-Youngest-of-them-all");
        List<String> for341 = new LinkedList<String>();
        for341.add("Grossman-the-Youngest-of-them-all");
        expected.put("CSE331", for331);
        expected.put("CSE403", for403);
        expected.put("CSE401", for401);
        expected.put("CSE332", for332);
        expected.put("CSE341", for341);
        Map<String, List<String>> map = MarvelParser.parseData("src/test/resources/marvel/data/staffSuperheroes.tsv");
        assertFalse("map should not be empty", map.isEmpty());
        assertEquals("maps should be equal", expected, map);
    }
}
