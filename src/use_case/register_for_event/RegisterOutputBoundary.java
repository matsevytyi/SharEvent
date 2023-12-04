/**
 * The RegisterOutputBoundary interface defines the contract for preparing views based on the outcome of the user registration for an event process.
 */

package USE_CASE.register_for_event;

import USE_CASE.delete_event.DeleteEventOutputData;

public interface RegisterOutputBoundary {
    void prepareSuccessCase();
}
