/**
 * @author Nithin
 */
module race.game.nithinfxrace {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fx to javafx.fxml;
    exports fx;
    exports fx.controllers;
    exports models.core;
    exports models.exceptions;
    exports models.traps;
    exports models.utilities;
    opens fx.controllers to javafx.fxml;
}