package com.youdevise.narrative.example;

import com.youdevise.test.narrative.*;

import org.junit.*;
import org.hamcrest.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class BasicArithmeticTest {
    private CalculatorActor operator = new CalculatorActor();

    @Test public void
    adds_two_numbers() {
        Given.the( operator).was_able_to( enter("2"))
                           .was_able_to( enter("+"))
                           .was_able_to( enter("2"));

        When.the( operator).attempts_to( enter("="));

        Then.the( operator).expects_that( the_displayed_value()).should_be( equalTo("4"));
    }

    @Test public void
    subtracts_two_numbers() {
        Given.the( operator).was_able_to( enter("3"))
                           .was_able_to( enter("-"))
                           .was_able_to( enter("1"));

        When.the( operator).attempts_to( enter("="));

        Then.the( operator).expects_that( the_displayed_value()).should_be( equalTo("2"));
    }

    private static Action<Calculator, CalculatorActor> enter(final String entry) {
        return new Action<Calculator, CalculatorActor>() {
            public void performFor(CalculatorActor actor) {
                actor.tool().enter(entry);
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
