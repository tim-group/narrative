package com.youdevise.test.narrative;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;

@RunWith(JMock.class)
public class ThenTest {
    private Mockery context = new Mockery();

    @SuppressWarnings("unchecked")
    @Test public void
    grabsTheDataToCheckUsingTheActor() {
        final StringActor actor = context.mock(StringActor.class);
        final Extractor<Character, StringActor> extractor = context.mock(Extractor.class);

        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(extractor); will(returnValue('a'));
        }});

        Then.the(actor).expects_that(extractor, is('a'));
    }

    @SuppressWarnings("unchecked")
    @Test(expected=AssertionError.class) public void
    failsIfTheGrabbedDataDoesNotMatch() {
        final StringActor actor = context.mock(StringActor.class);
        final Extractor<Character, StringActor> extractor = context.mock(Extractor.class);

        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(extractor); will(returnValue('b'));
        }});

        Then.the(actor).expects_that(extractor, is('a'));
    }

    @SuppressWarnings("unchecked")
    @Test public void
    canAssertMoreThanOnePieceOfData() {
        final StringActor actor = context.mock(StringActor.class);
        final Extractor<Character, StringActor> characterExtractor = context.mock(Extractor.class, "character extractor");
        final Extractor<String, StringActor> stringExtractor = context.mock(Extractor.class, "string extractor");

        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(characterExtractor); will(returnValue('a'));
            oneOf(actor).grabUsing(stringExtractor); will(returnValue("string"));
        }});

        Then.the(actor).expects_that(characterExtractor, is('a'))
            .and_also().expects_that(stringExtractor, is("string"));
    }

    @SuppressWarnings("unchecked")
    @Test(expected=AssertionError.class) public void
    canPassOnAnEarlierAssertionAndFailOnALaterOne() {
        final StringActor actor = context.mock(StringActor.class);
        final Extractor<Character, StringActor> characterExtractor = context.mock(Extractor.class, "character extractor");
        final Extractor<String, StringActor> stringExtractor = context.mock(Extractor.class, "string extractor");

        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(characterExtractor); will(returnValue('a'));
            oneOf(actor).grabUsing(stringExtractor); will(returnValue("not the string"));
        }});

        Then.the(actor).expects_that(characterExtractor, is('a'))
            .and_also().expects_that(stringExtractor, is("string"));
    }
}
