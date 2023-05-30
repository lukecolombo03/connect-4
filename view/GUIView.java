package all.view;

import all.controller.Features;

public interface GUIView {

  void refresh();

  void renderMessage(String message, boolean bigOrLittle);

  void setUpFeatures(Features features);

  void changeTurnIndicator(String message);
}
