package com.youdevise.narrative.example;

import com.youdevise.test.narrative.*;

import static org.hamcrest.Matchers.is;

import org.junit.*;
import org.hamcrest.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/*
    Tests for a very basic console-based calculator, to illustrate usage of the Narrative library.
*/

public class BasicArithmeticTest {
    /*
        You need to define an Actor to get started. The Actor performs various actions on the domain
        objects - in this case we have just one object, the calculator.
    */
    private CalculatorActor operator = new CalculatorActor();

    /*
        The tests themselves, in given-when-then style. Narrative provides everything used here
        except the press() and the_displayed_value() methods defined below, and the Hamcrest matcher
        equalTo().
    */
    @Test public void
    adds_two_numbers() {
        Given.the( operator).was_able_to( press('2'))
                            .was_able_to( press('+'))
                            .was_able_to( press('2'));

        When.the( operator).attempts_to( press('='));

        Then.the( operator).expects_that( the_displayed_value(), is("4"));
    }

    @Test public void
    subtracts_two_numbers() {
        Given.the( operator).was_able_to( press('3'))
                            .was_able_to( press('-'))
                            .was_able_to( press('1'));

        When.the( operator).attempts_to( press('='));

        Then.the( operator).expects_that( the_displayed_value(), is("2"));
    }

    /*
        Here we define press() and the_displayed_value(). They just delegate to the corresponding
        actions on the calculator.
    */
    private static Action<Calculator, CalculatorActor> press(final char keypress) {
        return new Action<Calculator, CalculatorActor>() {
            public void performFor(CalculatorActor actor) {
                actor.tool().press(keypress);
            }
        };
    }

    private static Extractor<String, CalculatorActor> the_displayed_value() {
        return new Extractor<String, CalculatorActor>() {
            public String grabFor(CalculatorActor actor) {
                return actor.tool().read();
            }
        };
    }

    /*
        The Actor that does all the work as commanded by the tests.
    */
    private static class CalculatorActor implements Actor<Calculator, CalculatorActor> {
        private Calculator calculator = new Calculator();

        public Calculator tool() {
            return calculator;
        }

        public void perform(Action<Calculator, CalculatorActor> action) {
            action.performFor(this);
        }

        public <DATA> DATA grabUsing(Extractor<DATA, CalculatorActor> extractor) {
            return extractor.grabFor(this);
        }
    }
}
