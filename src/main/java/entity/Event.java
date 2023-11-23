package entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Event {

    int idEvent;
    String eventName;
    String description;
    String type;
    String time;
    String date;
    float longitude;
    float latitude;
    String creator;


}
