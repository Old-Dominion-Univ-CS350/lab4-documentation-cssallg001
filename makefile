all: wordCounter.jar

wordCounter.jar: bin/edu/odu/cs/MostCommonWords.class bin/edu/odu/cs/WordCount.class bin/edu/odu/cs/WordCounter.class bin/edu/odu/cs/WordFilter.class bin/edu/odu/cs/TestWordFilter.class bin/edu/odu/cs/TestWordCounter.class
	cd bin; jar --create --file ../$@ .

bin/edu/odu/cs/%.class: src/main/java/edu/odu/cs/%.java
	javac -g -cp 'bin:src/main/java:lib/*' -sourcepath src/main/java -d bin $<

clean:
	-rm -rf bin *.jar 

test: wordCounter.jar
	java -cp 'wordCounter.jar:lib/*'  org.junit.platform.console.ConsoleLauncher -c edu.odu.cs.TestWordCounter

