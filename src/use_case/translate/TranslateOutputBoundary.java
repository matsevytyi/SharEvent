package use_case.translate;

import use_case.add_event.AddEventOutputData;

public interface TranslateOutputBoundary {

    void prepareSuccessView(TranslateOutputData event);

    void prepareFailView(String error);


}
