# Makefile for the grenobleEat application
# Usage :
#
# Before all you need to compile the files :
# make build
#
# To run the principal application :
# make run local_database_name port username password database_address
#

ifeq (run,$(firstword $(MAKECMDGOALS)))
  # use the rest as arguments for "run"
  ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  $(eval $(ARGS):;@:)
endif

build: clean
	javac -d build -cp ./app/src/main/resources/mysql-connector.jar ./app/src/main/java/grenobleeat/database/*.java ./app/src/main/java/grenobleeat/session/*.java ./app/src/main/java/grenobleeat/App.java

run:
	java -cp build:app/src/main/resources/mysql-connector.jar grenobleeat/App $(ARGS)

run_mehdi:
	java -cp build:app/src/main/resources/mysql-connector.jar grenobleeat/App baseGrenobleEats 3306 etudiant mypass123 79.88.105.10

doc: clean
	javadoc -d doc -private -protected -private ./app/src/main/java/grenobleeat/database/*.java ./app/src/main/java/grenobleeat/session/*.java ./app/src/main/java/grenobleeat/App.java

clean:
	if [ -d "build" ]; then rm -dr build; fi
	if [ -d "doc" ]; then rm -dr doc; fi
