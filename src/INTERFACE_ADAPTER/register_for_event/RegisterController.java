package INTERFACE_ADAPTER.register_for_event;

import USE_CASE.register_for_event.RegisterInputBoundary;
import USE_CASE.register_for_event.RegisterInputData;

public class RegisterController {

    final RegisterInputBoundary registerInputBoundary;

    public RegisterController(RegisterInputBoundary registerInputBoundary) {
        this.registerInputBoundary = registerInputBoundary;
    }

    public void execute(int eventId, String username){
        RegisterInputData registerInputData = new RegisterInputData(eventId, username);

        registerInputBoundary.execute(registerInputData);
    }


}
