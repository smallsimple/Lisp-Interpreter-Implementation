# This is a very simple makefile for building the Lisp interpreter
# project when using Java on stdsun. Feel free to add any improvements:
# e.g. pattern rules, automatic tracking of dependencies, etc. There
# is a lot of info about "make" on the web.

# Java compiler
JAVAC = javac

# Java compiler flags
JAVAFLAGS = -g 

# classpath
# CLASSPATH = ./sexp ./grammar

# Creating a .class file
# COMPILE = $(JAVAC) -classpath $(CLASSPATH) $(JAVAFLAGS)
COMPILE = $(JAVAC)  $(JAVAFLAGS)

# One of these should be the "main" class listed in Runfile
CLASS_FILES = MyInt.class Tokenizer.class SE.class Sp.class Ep.class Xp.class Yp.class Rp.class MyFunctions.class

# The first target is the one that is executed when you invoke
# "make". 

all: $(CLASS_FILES) 

# The line describing the action starts with <TAB>
%.class : %.java
	$(COMPILE) $<




clean: 
	rm -rf *.class 