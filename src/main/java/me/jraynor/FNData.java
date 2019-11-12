package me.jraynor;

import me.jraynor.bootstrap.IEngine;
import me.jraynor.bootstrap.Window;
import me.jraynor.controllers.MainController;
import me.jraynor.uison.UIMaster;
import me.jraynor.uison.misc.Input;

public class FNData extends IEngine {
    private final Window window;

    private FNData(int width, int height, String title) {
        window = new Window(width, height, true, false, true, title);
        window.start(this);
    }

    public void postInit() {
        UIMaster.createUIMaster(window, new MainController(window));
    }

    public void renderUI(double v) {
        UIMaster.update(window);
    }

    public void render(double v) {

    }

    public void update(double v) {
        Input.globalMouse();
    }


    public static void main(String[] args) {
        switch (args.length) {
            case 1:
                new FNData(Integer.parseInt(args[0]), Integer.parseInt(args[0]), "Data grabber");
                break;
            case 2:
                new FNData(Integer.parseInt(args[0]), Integer.parseInt(args[1]), "Data grabber");
                break;
            default:
                new FNData(1080, 720, "Data grabber");
        }
    }
}
