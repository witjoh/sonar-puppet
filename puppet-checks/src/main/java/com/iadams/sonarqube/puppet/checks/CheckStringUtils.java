/*
 * SonarQube Puppet Plugin
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Iain Adams and David RACODON
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.iadams.sonarqube.puppet.checks;

import java.util.regex.Pattern;

public class CheckStringUtils {

  private static final Pattern PATTERN_CONTAINING_VARIABLE_ENCLOSED_IN_BRACES = Pattern.compile(".*(?<!\\\\)\\$\\{(::)?(\\w+::)*\\w+}.*", Pattern.DOTALL);
  private static final Pattern PATTERN_CONTAINING_VARIABLE_NOT_ENCLOSED_IN_BRACES = Pattern.compile(".*(?<!\\\\)\\$(::)?(\\w+::)*\\w+.*", Pattern.DOTALL);
  private static final Pattern PATTERN_CONTAINING_ONLY_VARIABLE_ENCLOSED_IN_BRACES = Pattern.compile("(?<!\\\\)\\$\\{(::)?(\\w+::)*\\w+}");
  private static final Pattern PATTERN_CONTAINING_ONLY_VARIABLE_NOT_ENCLOSED_IN_BRACES = Pattern.compile("(?<!\\\\)\\$(::)?(\\w+::)*\\w+");
  private static final Pattern PATTERN_CONTAINING_SPECIAL_CHARACTER = Pattern.compile("\"|\\\\t|\\\\r|\\\\n|'|\\\\\\$");

  private CheckStringUtils() {
  }

  public static boolean containsVariable(String string) {
    return PATTERN_CONTAINING_VARIABLE_ENCLOSED_IN_BRACES.matcher(string).matches()
      || PATTERN_CONTAINING_VARIABLE_NOT_ENCLOSED_IN_BRACES.matcher(string).matches();
  }

  public static boolean containsNotEnclosedVariable(String string) {
    return PATTERN_CONTAINING_VARIABLE_NOT_ENCLOSED_IN_BRACES.matcher(string).matches();
  }

  public static boolean containsOnlyVariable(String string) {
    return PATTERN_CONTAINING_ONLY_VARIABLE_ENCLOSED_IN_BRACES.matcher(string).matches()
      || PATTERN_CONTAINING_ONLY_VARIABLE_NOT_ENCLOSED_IN_BRACES.matcher(string).matches();
  }

  public static boolean containsSpecialCharacter(String string) {
    return PATTERN_CONTAINING_SPECIAL_CHARACTER.matcher(string).find();
  }

}
