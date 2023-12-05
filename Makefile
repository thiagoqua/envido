MAIN_CLASS = Main
SOURCES = $(wildcard *.java)
CLASSES = $(SOURCES:.java=.class)

all: $(MAIN_CLASS)

%.class: %.java
	javac $<

$(MAIN_CLASS): $(CLASSES)
	java $(MAIN_CLASS)

clean:
	rm -f *.class
