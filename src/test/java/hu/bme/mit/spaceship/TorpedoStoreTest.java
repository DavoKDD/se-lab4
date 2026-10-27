package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {
    @Test
    void initialStockIsObservable() {
        TorpedoStore store = new TorpedoStore(3, 0.0);
        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void successfulFireConsumesRequestedAmount() {
        TorpedoStore store = new TorpedoStore(3, 0.0);
        assertTrue(store.fire(2));
        assertEquals(1, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void firingAllRemainingTorpedoesEmptiesStore() {
        TorpedoStore store = new TorpedoStore(2, 0.0);
        assertTrue(store.fire(2));
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void failedFirePreservesStock() {
        TorpedoStore store = new TorpedoStore(3, 1.0);
        assertFalse(store.fire(2));
        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void initiallyEmptyStoreCannotFire() {
        TorpedoStore store = new TorpedoStore(0, 0.0);
        assertTrue(store.isEmpty());
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void zeroAmountIsRejectedWithoutChangingStock() {
        TorpedoStore store = new TorpedoStore(3, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void negativeAmountIsRejectedWithoutChangingStock() {
        TorpedoStore store = new TorpedoStore(3, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void excessiveAmountIsRejectedWithoutChangingStock() {
        TorpedoStore store = new TorpedoStore(3, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(4));
        assertEquals(3, store.getTorpedoCount());
    }
}
