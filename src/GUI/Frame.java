package GUI;

import Game.Helpers.Data.FrameData;
import Game.Helpers.Enums.FrameButtonsEnum;
import Game.Pokemon.Trainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Frame extends JFrame implements FrameData {
    private static Frame instance;

    private boolean waitingForNext;
    private JTextArea textOutput;
    private ArrayList<Button> buttons;
    private PokemonComponent pokemonComponent;

    private FrameButtonsEnum currentButtonEnum;

    private Trainer player;
    private Trainer opponent;

    private Frame() {
        waitingForNext = false;
        currentButtonEnum = FrameButtonsEnum.NULL;
        buttons = new ArrayList<>();
        JFrame fr = new JFrame();
        fr.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fr.setTitle("Pokemon");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);

        // Add the panel to the frame
        fr.add(CreateContainerPanel());
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }

    public static Frame getInstance() {
        if(instance == null) {
            instance = new Frame();
        }
        return instance;
    }

    private JPanel CreateContainerPanel() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(2, 1));

        containerPanel.add(CreateTopPanel());
        containerPanel.add(CreateBottomPanel());

        return containerPanel;
    }

    private JPanel CreateTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.add(CreatePokemonComponent(), BorderLayout.CENTER);
        topPanel.add(CreateTextPanel(), BorderLayout.PAGE_START);
        return topPanel;
    }

    private PokemonComponent CreatePokemonComponent() {
        pokemonComponent = new PokemonComponent();
        pokemonComponent.setBackgroundImage(createBackgroundImage());
        return pokemonComponent;
    }

    private JPanel CreateTextPanel() {
        JPanel textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(FRAME_WIDTH, TOP_PANEL_TEXT_HEIGHT));
        textPanel.setLayout(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        textOutput = new JTextArea();
        textOutput.setEditable(false);
        textOutput.setLineWrap(true);
        textOutput.setWrapStyleWord(true);
        textOutput.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        textOutput.setFont(new Font("Courier New", Font.PLAIN, 16));
        textPanel.add(textOutput);

        return textPanel;
    }

    private JPanel CreateBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 4; i++) {
            Button button = new Button(i);
            button.setFont((new Font("Courier New", Font.PLAIN, 16)));
            button.addActionListener(e -> {
                switch (currentButtonEnum) {
                    case NEXT: {
                        if (button.getId() == 3) {
                            waitingForNext = true;
                        }
                        break;
                    }
                    case MOVE_ONE: {
                        if (button.getId() == 0) {
                            currentButtonEnum = FrameButtonsEnum.MOVE_TWO;
                            waitingForNext = true;
                        } else if (button.getId() == 1) {
                            currentButtonEnum = FrameButtonsEnum.POKEMON_ONE;
                            player.getCurrentPokemon().setCurrentMove(-1);
                            waitingForNext = true;
                        }
                        break;
                    }
                    case MOVE_TWO: {
                        player.getCurrentPokemon().setCurrentMove(button.getId());
                        waitingForNext = true;
                        break;
                    }
                    case POKEMON_ONE: {
                        if (button.getId() < player.getPokemonTotalNum() && player.getCurrentPokemon().getName().equals(player.getPokemon(button.getId()).getName())) {
                            waitingForNext = true;
                        }
                        if (button.getId() == 3) {
                            if (player.getPokemonTotalNum() <= button.getId()) {
                                waitingForNext = true;
                            }
                            else {
                                currentButtonEnum = FrameButtonsEnum.POKEMON_TWO;
                                waitingForNext = true;
                            }
                        }
                        else if (button.getId() < player.getPokemonTotalNum() &&
                        button.getId() < 3 &&
                        player.getPokemon(button.getId()).getHp() > 0) {
                            player.setCurrentPokemon(button.getId());
                            waitingForNext = true;
                        }
                        break;
                    }
                    case POKEMON_TWO: {
                        if (button.getId() < player.getPokemonTotalNum() && player.getCurrentPokemon().getName().equals(player.getPokemon(button.getId()).getName())) {
                            waitingForNext = true;
                        }
                        if (button.getId() == 3) {
                            waitingForNext = true;
                        }
                        else if (button.getId() + 3 < player.getPokemonTotalNum() &&
                        button.getId() < 3 &&
                                player.getPokemon(button.getId() + 3).getHp() > 0) {
                            player.setCurrentPokemon(button.getId() + 3);
                            waitingForNext = true;
                        }
                        break;
                    }
                }
            });
            bottomPanel.add(button);
            buttons.add(button);
        }
        return bottomPanel;
    }

    public void setTrainer(Trainer player, Trainer opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public void setPokemonImage(boolean player) {
        if (player) {
            pokemonComponent.setPlayerPokemon(this.player.getCurrentPokemon().getBackIcon(), true);
        }
        else {
            pokemonComponent.setPlayerPokemon(this.opponent.getCurrentPokemon().getFrontIcon(), false);
        }
    }

    public void removePokemonImage(boolean player) {
        if (player) {
            pokemonComponent.removePlayerPokemon(true);
        }
        else {
            pokemonComponent.removePlayerPokemon(false);
        }
    }

    private Image createBackgroundImage() {
        Image image = null;
        try {

            File f = new File(System.getProperty("user.dir") + "\\images\\Backgrounds\\" + BACKGROUND_IMAGE);
            image = ImageIO.read(f);
        } catch (IOException e) {
            // Nothing
        }

        return image.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT / 2, Image.SCALE_SMOOTH);
    }

    private void setText(String text)
    {
        textOutput.setText(text);
        textOutput.setCaretPosition(textOutput.getDocument().getLength());
    }

    public void setTextAndWaitForNext(String text) {
        setTextAndWaitForButton(text, FrameButtonsEnum.NEXT);
    }

    public void setTextAndWaitForMoveOne(String text) {
        setTextAndWaitForButton(text, FrameButtonsEnum.MOVE_ONE);
    }

    public void setTextAndWaitForMoveTwo(String text) {
        if (currentButtonEnum == FrameButtonsEnum.MOVE_TWO) {
            setTextAndWaitForButton(text, currentButtonEnum);
        }
    }

    public void setTextAndWaitForPokemonOne(String text, boolean battle) {
        if (battle) {
            if (currentButtonEnum == FrameButtonsEnum.POKEMON_ONE) {
                setTextAndWaitForButton(text, currentButtonEnum);
            }
        }
        else {
            setTextAndWaitForButton(text, FrameButtonsEnum.POKEMON_ONE);
        }
    }

    public void setTextAndWaitForPokemonTwo(String text) {
        if (currentButtonEnum == FrameButtonsEnum.POKEMON_TWO) {
            setTextAndWaitForButton(text, currentButtonEnum);
        }
    }

    private void setTextAndWaitForButton(String text, FrameButtonsEnum buttonEnum) {
        clearButtons();
        currentButtonEnum = buttonEnum;
        setText(text);
        switch (currentButtonEnum) {
            case NEXT:
                next();
                break;
            case MOVE_ONE:
                moveOne();
                break;
            case MOVE_TWO:
                moveTwo();
                break;
            case POKEMON_ONE:
                pokemonOne();
                break;
            case POKEMON_TWO:
                pokemonTwo();
                break;

        }
        waitForInput();
    }

    private void next() {
        buttons.get(3).setText("Next");
    }

    private void moveOne() {
        buttons.get(0).setText("Moves");
        buttons.get(1).setText("Pokemon");
    }

    private void moveTwo() {
        for (int i = 0; i < 4; i++) {
            buttons.get(i).setText(player.getCurrentPokemon().getMove(i).getName());
        }
    }

    private void pokemonOne() {
        for (int i = 0; i < player.getPokemonTotalNum() && i < 3; i++) {
            final String name = player.getPokemon(i).getName() + " HP " + player.getPokemon(i).getHp() + "/" + player.getPokemon(i).getHpMax();
            buttons.get(i).setText(name);
        }
        if (player.getPokemonTotalNum() > 3) {
            buttons.get(3).setText("Next");
        }
        else {
            buttons.get(3).setText("Back");
        }
    }

    private void pokemonTwo() {
        for (int i = 3; i < player.getPokemonTotalNum() && i < 6; i++) {
            final String name = player.getPokemon(i).getName() + " HP " + player.getPokemon(i).getHp() + "/" + player.getPokemon(i).getHpMax();
            buttons.get(i - 3).setText(name);
        }
        buttons.get(3).setText("Back");
    }

    private void waitForInput() {
        while(!waitingForNext) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Nothing
            }
        }
        waitingForNext = false;
    }

    private void clearButtons() {
        currentButtonEnum = FrameButtonsEnum.NULL;
        for(Button b : buttons) {
            b.setText("");
        }
    }
}
