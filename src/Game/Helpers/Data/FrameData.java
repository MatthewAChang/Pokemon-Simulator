package Game.Helpers.Data;

public interface FrameData {
    int FRAME_WIDTH = 600;
    int FRAME_HEIGHT = 800;

    int TOP_PANEL_IMAGE_HEIGHT  = 3 * (FRAME_HEIGHT / 2) / 4;
    int TOP_PANEL_TEXT_HEIGHT  = (FRAME_HEIGHT / 2) / 4;

    int PLAYER_POKEMON_X = FRAME_WIDTH / 10;
    int PLAYER_POKEMON_Y = Math.round(FRAME_HEIGHT / 6.4f);
    int OPPONENT_POKEMON_X = Math.round(FRAME_WIDTH / 1.6f);
    int OPPONENT_POKEMON_Y = FRAME_HEIGHT / 10;

    int PLAYER_POKEMON_STATUS_X = 310;
    int PLAYER_POKEMON_STATUS_Y = 220;

    int OPPONENT_POKEMON_STATUS_X = 40;
    int OPPONENT_POKEMON_STATUS_Y = 80;

    int PLAYER_POKEMON_STATUS_HEIGHT = 60;
    int OPPONENT_POKEMON_STATUS_HEIGHT = 50;
    int POKEMON_STATUS_WIDTH = 250;

    int POKEMON_NAME_X_OFFSET = 10;
    int POKEMON_NAME_Y_OFFSET = 20;
    int POKEMON_LV_X_OFFSET = 197;

    int HP_RECT_X_OFFSET = 40;
    int HP_RECT_Y_OFFSET = 30;
    int HP_RECT_WIDTH = 200;
    int HP_RECT_HEIGHT = 15;

    int HP_TITLE_X_OFFSET = 10;
    int HP_TITLE_Y_OFFSET = 42;

    int HP_STRING_X_OFFSET = 187;
    int HP_STRING_Y_OFFSET = 58;

    String BACKGROUND_IMAGE = "Grass.png";
}
