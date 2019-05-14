/*
 * Copyright ©2019 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2019 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package marvel;

import java.io.IOException;
import java.util.*;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/** Parser utility to load the Marvel Comics dataset. */
public class MarvelParser {

  /**
   * Reads the Marvel Universe dataset. Each line of the input file contains a character name and a
   * comic book the character appeared in, separated by a tab character
   *
   * @spec.requires filename is a valid file path
   * @param filename the file that will be read
   * @return a Map of key type String and value type Set<String>, where the keys are the character
   * names and the corresponding value is a set of the comic books that character has appeared in,
   * or null if the parsing was unsuccessful.
   */
  public static Map <String, Set<String>> parseData(String filename) {
    try {
      Reader reader = Files.newBufferedReader(Paths.get(filename));

      CsvToBean<Character> csvToBean = new CsvToBeanBuilder(reader)
              .withType(Character.class)
              .withIgnoreLeadingWhiteSpace(true)
              .withSeparator('\t')
              .build();

      Map<String, Set<String>> characterMap = new HashMap<>();
      Iterator<Character> csvUserIterator = csvToBean.iterator();

      // populates the map
      while (csvUserIterator.hasNext()) {
        Character csvChar = csvUserIterator.next();
        String charName = csvChar.getName();
        if (characterMap.containsKey(charName)) {
          characterMap.get(charName).add(csvChar.getBook());
        } else {
          Set<String> nextSet = new HashSet<>();
          nextSet.add(csvChar.getBook());
          characterMap.put(charName, nextSet);
        }
      }

      return characterMap;
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println(filename + ": file not found");
      System.exit(1);
    }
    catch(IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    return null;
  }
}
