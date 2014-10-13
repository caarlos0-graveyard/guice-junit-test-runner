# guice-junit-test-runner [![Build Status](https://travis-ci.org/caarlos0/guice-junit-test-runner.svg?branch=master)](https://travis-ci.org/caarlos0/guice-junit-test-runner) [![Stories in Ready](https://badge.waffle.io/caarlos0/github-integrator.png?label=ready&title=Ready)](https://waffle.io/caarlos0/github-integrator)

A very simple artifact, with no dependencies besides JUnit and Guice,
which is intended to make it easier to test your Guice code.

## Usage

Add the dependency to your `pom.xml`:

```xml
<dependency>
	<groupId>com.carlosbecker</groupId>
	<artifactId>guice-junit-test-runner</artifactId>
	<version>1.0-SNAPSHOT</version>
	<scope>test</scope>
</dependency>
```

> This is not in maven central yet, so this will not work just now.

Write your tests:

```java
@RunWith(GuiceTestRunner.class)
@GuiceModules(MyModule.class)
public class MyTest {
	@Inject
	private Something something;

	@Test
	public void testItWorks() throws Exception {
		assertThat(something.doSomething(), notNullValue());
	}
}
```

And that's it!
