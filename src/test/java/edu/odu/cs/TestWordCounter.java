package edu.odu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestWordCounter {

	/**
	 * Test method basic words
	 */
	@Test
	public final void testConstructor() {
		WordCounter wc = new WordCounter();
		assertThat(wc.numberOfDistinctWords(), is(0));
		Iterator<WordCount> iter = wc.iterator();
		assertThat(iter.hasNext(), is(false));
		assertThat(wc.toString(), notNullValue());
	}

	@Test
	public final void testAddNoDuplicates() {
		WordCounter wc = new WordCounter();
		wc.addInstance("hello");
		wc.addInstance("world");
		wc.addInstance("Hello");

		assertThat(wc.numberOfDistinctWords(), is(3));
		assertThat(wc.toString(), containsString("hello"));
		assertThat(wc.toString(), containsString("Hello"));
		assertThat(wc.toString(), containsString("world"));

		// brute force
		Iterator<WordCount> iter = wc.iterator();
		for (int i = 0; i < 3; ++i) {
			assertThat(iter.hasNext(), is(true));
			WordCount w = iter.next();
			assertThat(w.count, is(1));
			assertThat(w.word, anyOf(is("hello"), is("Hello"), is("world")));
		}
		assertThat(iter.hasNext(), is(false));



		// Although it's not obvious, the following tests WordCounter.iterator()
		assertThat(wc, hasItem(new WordCount("hello", 1)));
		assertThat(wc, hasItem(new WordCount("Hello", 1)));
		assertThat(wc, hasItem(new WordCount("world", 1)));

		// A more elegant approach
		WordCount[] expected = { new WordCount("hello", 1),
				new WordCount("Hello", 1),
				new WordCount("world", 1)
		};
		assertThat(wc, containsInAnyOrder(expected));
	}


	@Test
	public final void testAddWithDuplicates() {
		WordCounter wc = new WordCounter();
		wc.addInstance("hello");
		wc.addInstance("world");
		wc.addInstance("Hello");
		wc.addInstance("world");
		wc.addInstance("world");

		assertThat(wc.numberOfDistinctWords(), is(3));
		assertThat(wc.toString(), containsString("hello"));
		assertThat(wc.toString(), containsString("Hello"));
		assertThat(wc.toString(), containsString("world"));


		WordCount[] expected = { new WordCount("hello", 1),
				new WordCount("Hello", 1),
				new WordCount("world", 3)
		};
		assertThat(wc, containsInAnyOrder(expected));
	}
	
	@Test
	public final void easyPass() {
		assertEquals(1,1);
	}



}


