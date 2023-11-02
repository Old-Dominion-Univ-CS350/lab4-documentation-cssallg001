package edu.odu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;



public class TestWordFilter {


  /**
   * Test method basic words
   */
  @Test
  public final void testBasicWords() {
    assertThat(WordFilter.filter("abc"), is("abc"));
    assertThat(WordFilter.filter("AbC"), is("abc"));
  }

  @Test
  public final void testAlphabeticPlusOthers() {
    assertThat(WordFilter.filter("a_bc"), is("a"));
    assertThat(WordFilter.filter("ab..c....c"), is("ab"));
    assertThat(WordFilter.filter("??abc"), is("abc"));
    assertThat(WordFilter.filter("abc123def"), is("abc"));
    assertThat(WordFilter.filter("123abc456def"), is("abc"));
  }

@Test
  public final void testHyphenation() {
    // Hyphens between two alphabetic characters are treated as letters
    assertThat(WordFilter.filter("-abc"), is("abc"));
    assertThat(WordFilter.filter("ab-C"), is("ab-c"));
    assertThat(WordFilter.filter("ab-cd-e"), is("ab-cd-e"));
    assertThat(WordFilter.filter("abc-1def"), is("abc"));
    assertThat(WordFilter.filter("ab--cd"), is("ab"));
    assertThat(WordFilter.filter("ab-"), is("ab"));
  }

}
