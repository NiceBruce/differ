run-dist:
	@./build/install/app/bin/app

build:
	./gradlew installDist
	./gradlew test
	./gradlew checkstyleMain
	./gradlew checkstyleTest
	
report:
	./gradlew jacocoTestReport

run:
	./gradlew run

.PHONY: build
