package com.youdevise.test.narrative;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;

@RunWith(JMock.class)
public class ThenTest {
    private final Mockery context = new Mockery();

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
    canAssertForDifferentActors() {
        final StringActor actor = context.mock(StringActor.class);
        final BooleanActor otherActor = context.mock(BooleanActor.class);
        final Extractor<Character, StringActor> characterExtractor = context.mock(Extractor.class, "character extractor");
        final Extractor<Boolean, BooleanActor> stringExtractor = context.mock(Extractor.class, "string extractor");

        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(characterExtractor); will(returnValue('a'));
            oneOf(otherActor).grabUsing(stringExtractor); will(returnValue(true));
        }});

        Then.the(actor).expects_that(characterExtractor, is('a'))
        .and_the(otherActor).expects_that(stringExtractor, is(true));
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
                       .and_that(stringExtractor, is("string"));
    }
    
    @SuppressWarnings("unchecked")
    @Test public void
    canMixCallsTo_ExpectsThat_AndTo_AndThat() {
        final StringActor actor = context.mock(StringActor.class);
        final Extractor<Character, StringActor> characterExtractor = context.mock(Extractor.class, "character extractor");
        final Extractor<String, StringActor> stringExtractor = context.mock(Extractor.class, "string extractor");
        final Extractor<Integer, StringActor> intExtractor = context.mock(Extractor.class, "int extractor");
        final Extractor<Double, StringActor> doubleExtractor = context.mock(Extractor.class, "double extractor");

        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(characterExtractor); will(returnValue('a'));
            oneOf(actor).grabUsing(stringExtractor); will(returnValue("string"));
            oneOf(actor).grabUsing(intExtractor); will(returnValue(1));
            oneOf(actor).grabUsing(doubleExtractor); will(returnValue(2.5));
        }});

        Then.the(actor).and_that(characterExtractor, is('a'))
            .and_that(stringExtractor, is("string"))
            .expects_that(intExtractor, is(1))
            .expects_that(doubleExtractor, is(2.5));
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
                       .expects_that(stringExtractor, is("string"));
    }
}
