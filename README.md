# guice-junit-test-runner [![Build Status](https://travis-ci.org/caarlos0/guice-junit-test-runner.svg?branch=master)](https://travis-ci.org/caarlos0/guice-junit-test-runner) [![Coverage Status](https://coveralls.io/repos/caarlos0/guice-junit-test-runner/badge.png?branch=master)](https://coveralls.io/r/caarlos0/guice-junit-test-runner?branch=master) [![Stories in Ready](https://badge.waffle.io/caarlos0/guice-junit-test-runner.png?label=ready&title=Ready)](https://waffle.io/caarlos0/guice-junit-test-runner)

A very simple artifact, with no dependencies besides JUnit and Guice,
which is intended to make it easier to test your Guice code.

## Usage

Add the dependency to your `pom.xml`:

```xml
<dependency>
	<groupId>com.carlosbecker</groupId>
	<artifactId>guice-junit-test-runner</artifactId>
	<version>1.1</version>
	<scope>test</scope>
</dependency>
```

Write your tests:

```java
@RunWith(GuiceTestRunner.class)
@GuiceModules(MyModule.class)
public class MyTest {
	@Inject
	private Something something;

	@Test
	public void testItWorks() throws Exception {
		Assert.assertThat(something.doSomething(), CoreMatchers.notNullValue());
	}
}
```

And that's it!

## How to contribute

Fork repository, make changes, send a pull request. I will review
your changes and apply them to the `master` branch shortly. To
avoid frustration, previously build your branch with:

```sh
mvn clean install -Pqulice
```

## Got questions?

If you have questions or general suggestions, don't hesitate to submit
a new [Github issue](/issues/new).

