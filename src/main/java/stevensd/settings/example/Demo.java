package stevensd.settings.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stevensd.settings.Setting;


public class Demo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    // Create the controller for the settings dialog.

    // NOTE: ConcreteSettingsDialogController is an empty concrete implementation of SimpleSettingsDialogController.
    // For your application, subclass SimpleSettingsDialogController, NOT ConcreteSettingsDialogController.
    ConcreteSettingsDialogController<Setting> dialogController = new ConcreteSettingsDialogController<>(primaryStage, Setting.class);

    // Add settings by providing urls to *.fxml documents and corresponding controllers.
    Setting s1 = dialogController.createAndAdd("setting1", "/exampleSettings/setting1.fxml", new Setting1Controller());
    Setting s2 = dialogController.createAndAdd("setting2", "/exampleSettings/setting2.fxml", new Setting2Controller());

    // Same as above except that these are sub-settings (belong under a more general setting) so there parent setting must be provided
    dialogController.createAndAdd("setting2-1", "/exampleSettings/setting2-1.fxml", new Setting21Controller(), s2);
    dialogController.createAndAdd("setting2-2", "/exampleSettings/setting2-2.fxml", new Setting22Controller(), s2);

    // Create and show the settings dialog. (The DialogController knows how to load itself)
    Scene scene = new Scene(dialogController.getPane());
    primaryStage.setTitle("Settings");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


}
