# Artist Corner Store

An online E-store system called "Artist Corner" built in Java 8=>11, TypeScript, HTML, CSS, and JavaScript.

## Team

- Alex Hetrick
- Alex Martinez
- Kara Kolodinsky
- Jonathan Campbell
- Daniel Kench

## Prerequisites

- Java 8=>11 (Make sure to have correct JAVA_HOME setup in your environment)
- Maven
- Node.js
- npm package manager

## How to run it

1. Clone the repository and go to the root directory.
2. Cd to the 'estore-api' folder and execute 'mvn compile exec:java'
3. In a seperate terminal, cd to the "estore-ui" folder and execute 'ng serve --open'
4. Open in your browser 'http://localhost:4200/'

## Known bugs and disclaimers

(It may be the case that your implementation is not perfect.)

Document any known bug or nuisance.
If any shortcomings, make clear what these are and where they are located.

## How to test it

The Maven build script provides hooks for run unit tests and generate code coverage
reports in HTML.

To run tests on all tiers together do this:

1. Execute `mvn clean test jacoco:report`
2. Open in your browser the file at `estore-api/target/site/jacoco/index.html`

To run tests on a single tier do this:

1. Execute `mvn clean test-compile surefire:test@tier jacoco:report@tier` where `tier` is one of `controller`, `model`, `persistence`
2. Open in your browser the file at `estore-api/target/site/jacoco/{controller, model, persistence}/index.html`

To run tests on all the tiers in isolation do this:

1. Execute `mvn exec:exec@tests-and-coverage`
2. To view the Controller tier tests open in your browser the file at `estore-api/target/site/jacoco/model/index.html`
3. To view the Model tier tests open in your browser the file at `estore-api/target/site/jacoco/model/index.html`
4. To view the Persistence tier tests open in your browser the file at `estore-api/target/site/jacoco/model/index.html`

\*(Consider using `mvn clean verify` to attest you have reached the target threshold for coverage)

## How to generate the Design documentation PDF

1. Access the `PROJECT_DOCS_HOME/` directory
2. Execute `mvn exec:exec@docs`
3. The generated PDF will be in `PROJECT_DOCS_HOME/` directory

## How to setup/run/test program

1. Tester, first obtain the Acceptance Test plan
2. IP address of target machine running the app
3. Execute **\_\_\_\_**
4. ...
5. ...

## License

MIT License

See LICENSE for details.
