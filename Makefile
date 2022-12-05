# Makefile for the grenobleEat application
# Usage :
#
# Before all you need to compile the files :
# make build
#
# To run the principal application :
# make run
#

build:
	javac -d build -cp ./app/src/main/resources/mysql-connector.jar ./app/src/main/java/grenobleeat/database/*.java ./app/src/main/java/grenobleeat/session/*.java ./app/src/main/java/grenobleeat/App.java

run:
	java -cp build:app/src/main/resources/mysql-connector.jar grenobleeat/App

