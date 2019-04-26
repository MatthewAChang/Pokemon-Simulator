package Game.Helpers.Data;

public interface FrameData {
    int FRAME_WIDTH = 600;
    int FRAME_HEIGHT = 800;

    int TOP_PANEL_IMAGE_HEIGHT  = 3 * (FRAME_HEIGHT / 2) / 4;
    int TOP_PANEL_TEXT_HEIGHT  = (FRAME_HEIGHT / 2) / 4;

    int PLAYER_POKEMON_X = FRAME_WIDTH / 10;
    int PLAYER_POKEMON_Y = (int)(FRAME_HEIGHT / 6.4f);
    int OPPONENT_POKEMON_X = (int)(FRAME_WIDTH / 1.6f);
    int OPPONENT_POKEMON_Y = FRAME_HEIGHT / 10;

    String BACKGROUND_IMAGE = "Grass.png";
}
