package com.youdevise.test.narrative;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class HashMapStashTest {
    private Stash stash = new HashMapStash();
    
    @Test public void
    doesNotContainAnyElementsByDefault() {
        assertFalse(stash.contains(new Object()));
    }
    
    @Test public void
    containsAnElementAfterItHasBeenAdded() {
        stash.put("key", "value");
        
        assertTrue(stash.contains("key"));
    }
    
    @Test public void
    getsTheStashedValue() {
        stash.put("key", "value");
        
        assertThat(stash.get("key", String.class), Matchers.is("value"));
    }
    
    @Test(expected=ClassCastException.class) public void
    throwsIfTheRequestedObjectIsNotOfTheRequestedType() {
        stash.put("key", new Object());
        
        stash.get("key", String.class);
    }
    
    @Test public void
    getsNullIfItDoesNotContainTheKey() {
        assertNull(stash.get("key", Object.class));
    }
}
