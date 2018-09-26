JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Main.java \
	Set.java \
	Identifier.java \
	SetInterface.java \
	IdentInterface.java 


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
