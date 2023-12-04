/**
 * The RegisterController class is responsible for handling user registration requests and interacting with the corresponding interactor.
 */

package INTERFACE_ADAPTER.register_for_event;

import USE_CASE.register_for_event.RegisterInputBoundary;
import USE_CASE.register_for_event.RegisterInputData;

public class RegisterController {

    final RegisterInputBoundary registerInputBoundary;

    public RegisterController(RegisterInputBoundary registerInputBoundary) {
        this.registerInputBoundary = registerInputBoundary;
    }

    /**
     * Executes the user registration process.
     *
     * @param eventId  The ID of the event for which the user is registering.
     * @param username The username of the user registering for the event.
     */
    public void execute(int eventId, String username){
        RegisterInputData registerInputData = new RegisterInputData(eventId, username);

        registerInputBoundary.execute(registerInputData);
    }


}
