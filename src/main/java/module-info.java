module cz.upce.fei.bpog1maventemplate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cz.upce.fei.bpog1maventemplate to javafx.fxml;
    exports cz.upce.fei.bpog1maventemplate;
}
