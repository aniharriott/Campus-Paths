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
   * @return a Map of key type String and value type Set<String>, where the keys are the book
   * names and the corresponding value is a set of the characters that have appeared in that book,
   * or null if the parsing was unsuccessful.
   */
  public static Map <String, Set<String>> parseData(String filename) {
    try {
      Reader reader = Files.newBufferedReader(Paths.get(filename));

      CsvToBean<MarvelBean> csvToBean = new CsvToBeanBuilder<MarvelBean>(reader)
              .withType(MarvelBean.class)
              .withIgnoreLeadingWhiteSpace(true)
              .withSeparator('\t')
              .build();

      Map<String, Set<String>> marvelMap = new HashMap<>();
      Iterator<MarvelBean> csvMarvelIterator = csvToBean.iterator();

      // populates the map
      while (csvMarvelIterator.hasNext()) {
        MarvelBean bean = csvMarvelIterator.next();
        String book = bean.getBook();
        if (marvelMap.containsKey(book)) {
          marvelMap.get(book).add(bean.getHero());
        } else {
          Set<String> nextSet = new HashSet<>();
          nextSet.add(bean.getHero());
          marvelMap.put(book, nextSet);
        }
      }

      return marvelMap;
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
