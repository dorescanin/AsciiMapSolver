# Path following application for ASCII maps

This application implements a simple path finding algorithm that can traverse an ASCII map that looks like this:
```
  @---+
      B
K-----|--A
|     |  |
|  +--E  |
|  |     |
+--E--Ex C
   |     |
   +--F--+
```
Assuming `@` is the starting position and `x` is the end position, application output will be:
- Letters ```BEEFCAKE```
- Path as characters ```@---+B||E--+|E|+--F--+|C|||A--|-----K|||+--E--Ex```

## Getting Started


### Prerequisites

In order to build and run the application, you will need:
- Java 8 or higher
- Maven (newest version if possible)

### Installing

Running `mvn install` will build the runnable jar file in `target` folder.

### Running the tests

Run `mvn test`.

### Running the application

After the jar file is build, find it in `target` folder and copy one map from `test` folder, e.g. `map1.txt`, so both jar and map file are in the same folder.

Then, run the application with:
```
java -jar softwaresauna-1.0-SNAPSHOT.jar map1.txt
```
Note: name of jar file might be different, depending on current version.

## Application description

The application uses a simple algorithm for finding a path from beginning to end, while "collecting" uppercase letters along the way.

It is assumed that the map is valid, which means:
- exactly one `@` character is present
- exactly one `x` character is present
- at every time, the algorithm will assume that there is exactly one valid next move possible - this means that there is NO backtracking
- the map is joined and traversable - no "islands" are supported

Supported characters:
- @
- x
- \-
- |
- \+
- letters A-Z
 
Note: no strict checks are being made no positioned characters on map, algorithm's only job is to find a way from `@` to `x` according to rules mentioned above.

## Further notes and possible improvements

The application has been developed and tested only on a Windows machine. Please drop a line if it doesn't work on other platforms.

Only `\r\n` as line separators are officially supported at this time.

Currently, the application is non-interactive. One possible improvement would be to see step by step how the algorithm is doing.

More tests could be developed to test various edge cases.
