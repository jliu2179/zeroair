package zeroair.gamestates.states;
import zeroair.Launcher;
import zeroair.PlayWindow;
import zeroair.util.buttons.*;
import zeroair.gamestates.GameStateManager;

import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;

public class PauseState implements State {

  BackButton resume = new BackButton(352, 300, 96, 32, "zeroair/assets/texts/hovered.png", "zeroair/assets/texts/resume.png");
  SettingsButton settings = new SettingsButton(352, 350, 96, 32, "zeroair/assets/texts/hovered.png", "zeroair/assets/texts/settings.png");
  RTMenuButton rToMenu = new RTMenuButton(352, 400, 96, 32, "zeroair/assets/texts/hovered.png", "zeroair/assets/texts/rtmenu.png");
  ExitButton exit = new ExitButton(352, 450, 96, 32, "zeroair/assets/texts/hovered.png", "zeroair/assets/texts/exit.png");

  private boolean escPressed;
  private PlayState playState;

  public PauseState(PlayState playState) {
    this.playState = playState;
  }

  public void processInput(double x, double y, boolean clicked, boolean escPressed) {
    resume.processInput(x, y, clicked, escPressed);
    settings.processInput(x, y, clicked, escPressed);
    rToMenu.processInput(x, y, clicked, escPressed);
    exit.processInput(x, y, clicked, escPressed);
    this.escPressed = escPressed;
  }

  public void update(long arg0) {
    PlayWindow.setClicked(false);
    PlayWindow.setESCPressed(false);
    resume.update(this, playState);
    settings.update();
    rToMenu.update();
    exit.update();

    if(escPressed) {
      GameStateManager.popState();
      GameStateManager.pushState(new ResumeState(playState));
    }
  }

  public void render(GraphicsContext graphicsContext) {
    playState.render(graphicsContext);
    resume.render(graphicsContext);
    settings.render(graphicsContext);
    rToMenu.render(graphicsContext);
    exit.render(graphicsContext);
  }
}
