package controller;

import java.io.FileNotFoundException;

import view.ViewListener;

/**
 * Created by vidojemihajlovikj on 7/26/23.
 */
public interface IControllerView extends ViewListener {
  void run();

  void handleSepiaEvent();
  void handleLoadPPMEvent() throws FileNotFoundException;
  void handleSavePPMEvent() throws FileNotFoundException;
}

