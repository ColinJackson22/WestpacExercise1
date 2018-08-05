package helpers;

import static helpers.Method.getCurrentClassName;
import static helpers.Method.getCurrentMethodName;

public class StepManagement {

    public static String getCurrentStepRef(){
        return String.format("%s.%s", getCurrentClassName(3), getCurrentMethodName(3));
    }

}
