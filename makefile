Tester.class:
	javac *.java
    
clean:
	rm -f *.class
	
run: Tester.class
	java -Xmx64m Tester