package com.elevator_project.first_floor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.game.*;

public class FirstFloor extends Floor {

    private FirstFloorFirstSide firstSide;
    private FirstFloorSecondSide secondSide;
    private FirstFloorThirdSide thirdSide;
    private FirstFloorFourthSide fourthSide;

    private BoxQuest boxQuest;

    public FirstFloor() {
        initParts();
    }

    private void initParts() {
        firstSide = new FirstFloorFirstSide();
        secondSide = new FirstFloorSecondSide();
        thirdSide = new FirstFloorThirdSide();
        fourthSide = new FirstFloorFourthSide();
        boxQuest = new BoxQuest();
        parts.add(firstSide);
        parts.add(secondSide);
        parts.add(thirdSide);
        parts.add(fourthSide);
        parts.add(boxQuest);
    }

    public void moveToBox() {
        partIndex = GameManager.getGameState().getPartIndex();
        move(partIndex, partIndex + 1);

    }

    @Override
    public void back() {
        partIndex = GameManager.getGameState().getPartIndex();
        move(partIndex, partIndex - 1);
    }

    @Override
    protected void move (int hide, int show) {
        App.getSoundManager().playSteps();
        if (show != 4) {
            arrows.show();
            downArrow.hide();
        } else {
            arrows.hide();
            downArrow.show();
            if (GameManager.getGameState().isBoxQuestSolved()) {
                GameManager.getInsulatingTape().show();
            }
        }
        GameManager.getGameState().setPartIndex(show);
        SaveManager.saveAutosave();
        if (hide == 2) {
            door.hide();
        }
        groups.get(hide).setVisible(false);
        groups.get(show).setVisible(true);
        if (show == 2) {
            door.show();
        } else if (show == 3) {
            fourthSide.changeWindow();
            if (
                GameManager.getGameState().isBoxQuestSolved() &&
                    GameManager.getInsulatingTape().getIndexInInventory() == -1
            ) {
                GameManager.getInsulatingTape().hide();
            }
        }
    }

    @Override
    public void render() {
        super.render();
        if (!GameManager.getGameState().isBoxQuestSolved()) {
            GameManager.getGameState().setDoorAvailable(false);
            SaveManager.saveAutosave();
        }
        if (
            GameManager.getInsulatingTape().getIndexInInventory() == -1 &&
                !GameManager.getGameState().isButtonAvailable()
        ) {
            GameManager.getInsulatingTape().render();
            if (!GameManager.getGameState().isBoxQuestSolved()||
            GameManager.getGameState().getPartIndex() != 4) {
                GameManager.getInsulatingTape().hide();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        GameManager.getInsulatingTape().dispose();
    }
}
