/**
 * @author Nithin
 */
module race.game.nithinfxrace {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fx to javafx.fxml;
    exports fx;
}