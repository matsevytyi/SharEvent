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

//    private void updateViewEventState(Event event) {
//        // Logic to update the ViewEventState based on the changes in the Event object
//        // For example, updating the registeredUsers field in ViewEventState
//        ViewEventState viewEventState = new ViewEventState();
//        viewEventState.setDetails(
//                event.getEventName(),
//                event.getDescription(),
//                event.getEventDate(),
//                event.getEventTime(),
//                event.getCreator().getName(),
//                event.getEventAttendants().toString() // Update with the latest registered users
//        );
//        // Update the view or UI with the updated ViewEventState
//    }
}
