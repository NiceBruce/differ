run-dist:
	@./build/install/app/bin/app

build:
	./gradlew installDist
	./gradlew test
	./gradlew checkstyleMain
	./gradlew checkstyleTest

run:
	./gradlew run

.PHONY: build