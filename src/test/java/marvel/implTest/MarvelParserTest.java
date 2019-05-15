package marvel.implTest;

import marvel.MarvelParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public final class MarvelParserTest {

    @Test
    public void testMarvelParser() {
        Map<String, Set<String>> expected = new HashMap<String, Set<String>>();
        Set<String> for331 = new HashSet<String>();
        for331.add("Ernst-the-Bicycling-Wizard");
        for331.add("Notkin-of-the-Superhuman-Beard");
        for331.add("Perkins-the-Magical-Singing-Instructor");
        for331.add("Grossman-the-Youngest-of-them-all");
        Set<String> for403 = new HashSet<String>();
        for403.add("Ernst-the-Bicycling-Wizard");
        for403.add("Notkin-of-the-Superhuman-Beard");
        Set<String> for401 = new HashSet<String>();
        for401.add("Perkins-the-Magical-Singing-Instructor");
        Set<String> for332 = new HashSet<String>();
        for332.add("Grossman-the-Youngest-of-them-all");
        Set<String> for341 = new HashSet<String>();
        for341.add("Grossman-the-Youngest-of-them-all");
        expected.put("CSE331", for331);
        expected.put("CSE403", for403);
        expected.put("CSE401", for401);
        expected.put("CSE332", for332);
        expected.put("CSE341", for341);
        Map<String, Set<String>> map = MarvelParser.parseData("src/test/resources/marvel/data/staffSuperheroes.tsv");
        assertFalse("map should not be empty", map.isEmpty());
        assertEquals("maps should be equal", expected, map);
    }
}
