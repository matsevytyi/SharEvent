//package interface_adapter.translate;
//
//import use_case.translate.TranslateInputBoundary;
//import use_case.translate.TranslateInputData;
//
//public class TranslateController {
//    private final TranslateInputBoundary translateInputBoundary;
//
//    public TranslateController(TranslateInputBoundary translateInputBoundary) {
//        this.translateInputBoundary = translateInputBoundary;
//    }
//
//    public void execute(int eventId, String language){
//        TranslateInputData translateInputData = new TranslateInputData(
//                eventId, language);
//
//        translateInputBoundary.execute(translateInputData);
//    }
//
//}
