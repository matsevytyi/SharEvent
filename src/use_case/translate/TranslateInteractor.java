//package use_case.translate;
//
//import data_access.EventDataAccessInterface;
//import data_access.GoogleTranslationDAOInterface;
//import entity.Event;
//
//public class TranslateInteractor implements TranslateInputBoundary {
//
//    final EventDataAccessInterface eventDataAccessObject;
//
//    final TranslateOutputBoundary translatePresenter;
//
//    final GoogleTranslationDAOInterface translationDAO ;
//
//
//
//
//    public TranslateInteractor(EventDataAccessInterface eventDataAccessInterface, TranslateOutputBoundary translatePresenter, GoogleTranslationDAOInterface translationDAO) {
//        this.eventDataAccessObject = eventDataAccessInterface;
//        this.translatePresenter = translatePresenter;
//
//        this.translationDAO = translationDAO;
//    }
//
//    @Override
//    public void execute(TranslateInputData translateInputData) {
//        Event event = eventDataAccessObject.getEventById(translateInputData.getEventId());
//
//        if (event != null) {
//            String translatedName = translationDAO.translate(event.getEventName(), translateInputData.getLanguage());
//            String translatedDescription = translationDAO.translate(event.getDescription(), translateInputData.getLanguage());
//            TranslateOutputData translateOutputData = new TranslateOutputData(translatedName, translatedDescription);
//            translatePresenter.prepareSuccessView(translateOutputData);
//        } else {
//            translatePresenter.prepareFailView("Event not found");
//        }
//    }
//    }
//}
