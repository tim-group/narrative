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
        final Actor<String> actor = context.mock(Actor.class);
        final Extractor<Character, String> extractor = context.mock(Extractor.class); 
        
        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(extractor); will(returnValue('a'));
        }});
        
        Then.the(actor).expectsThat(extractor).shouldBe(is('a'));
    }
    
    @SuppressWarnings("unchecked")
    @Test(expected=AssertionError.class) public void
    failsIfTheGrabbedDataDoesNotMatch() {
        final Actor<String> actor = context.mock(Actor.class);
        final Extractor<Character, String> extractor = context.mock(Extractor.class); 
        
        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(extractor); will(returnValue('b'));
        }});
        
        Then.the(actor).expectsThat(extractor).shouldBe(is('a'));
    }
    
    @SuppressWarnings("unchecked")
    @Test public void
    canAssertMoreThanOnePieceOfData() {
        final Actor<String> actor = context.mock(Actor.class);
        final Extractor<Character, String> characterExtractor = context.mock(Extractor.class, "character extractor"); 
        final Extractor<String, String> stringExtractor = context.mock(Extractor.class, "string extractor"); 
        
        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(characterExtractor); will(returnValue('a'));
            oneOf(actor).grabUsing(stringExtractor); will(returnValue("string"));
        }});
        
        Then.the(actor).expectsThat(characterExtractor).shouldBe(is('a'))
             .andAlso().expectsThat(stringExtractor).shouldBe(is("string"));
    }
    
    @SuppressWarnings("unchecked")
    @Test(expected=AssertionError.class) public void
    canPassOnAnEarlierAssertionAndFailOnALaterOne() {
        final Actor<String> actor = context.mock(Actor.class);
        final Extractor<Character, String> characterExtractor = context.mock(Extractor.class, "character extractor"); 
        final Extractor<String, String> stringExtractor = context.mock(Extractor.class, "string extractor"); 
        
        context.checking(new Expectations() {{
            oneOf(actor).grabUsing(characterExtractor); will(returnValue('a'));
            oneOf(actor).grabUsing(stringExtractor); will(returnValue("not the string"));
        }});
        
        Then.the(actor).expectsThat(characterExtractor).shouldBe(is('a'))
             .andAlso().expectsThat(stringExtractor).shouldBe(is("string"));
    }
}
