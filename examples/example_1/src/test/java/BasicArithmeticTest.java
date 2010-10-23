package com.youdevise.narrative.example;

import com.youdevise.test.narrative.*;

import org.junit.*;
import org.hamcrest.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class BasicArithmeticTest {
    private Actor<Calculator> operator = new CalculatorActor();

    @Test public void
    adds_two_numbers() {
        Given.the( operator).was_able_to( press('2'))
                            .was_able_to( press('+'))
                            .was_able_to( press('2'));

        When.the( operator).attempts_to( press('='));

        Then.the( operator).expects_that( the_displayed_value()).should_be( equalTo("4"));
    }

    @Test public void
    subtracts_two_numbers() {
        Given.the( operator).was_able_to( press('3'))
                            .was_able_to( press('-'))
                            .was_able_to( press('1'));

        When.the( operator).attempts_to( press('='));

        Then.the( operator).expects_that( the_displayed_value()).should_be( equalTo("2"));
    }

    private static Action<Calculator> press(final char keypress) {
        return new Action<Calculator>() {
            public void performFor(Calculator calculator, Stash stash) {
                calculator.press(keypress);
            }
        };
    }

    private static Extractor<String, Calculator> the_displayed_value() {
        return new Extractor<String, Calculator>() {
            public String grabFrom(Calculator calculator, Stash stash) {
                return calculator.read();
            }
        };
    }

    private static class CalculatorActor implements Actor<Calculator> {
        private Calculator calculator = new Calculator();

        public void perform(Action<Calculator> action) {
            action.performFor(calculator, new HashMapStash());
        }

        public <DATA> DATA grabUsing(Extractor<DATA, Calculator> extractor) {
            return extractor.grabFrom(calculator, new HashMapStash());
        }
    }
}
