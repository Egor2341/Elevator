package com.elevator_project.first_floor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.game.App;
import com.elevator_project.game.Floor;
import com.elevator_project.game.RoomPart;

public class FirstFloor extends Floor {

    private FirstFloorFirstSide firstSide;
    private FirstFloorSecondSide secondSide;
    private FirstFloorThirdSide thirdSide;
    private FirstFloorFourthSide fourthSide;

    private BoxQuest boxQuest;
    private int boxQuestIndex;

    public FirstFloor() {
        partIndex = 0;
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
        boxQuestIndex = 4;
    }

    public void moveToBox() {
        move(partIndex, boxQuestIndex);

    }

    @Override
    public void back() {
        move(boxQuestIndex, partIndex);
    }

    @Override
    protected void move (int hide, int show) {
        App.getSoundManager().playSteps();
        if (show != boxQuestIndex) {
            arrows.show();
            downArrow.hide();
            partIndex = show;
        } else {
            arrows.hide();
            downArrow.show();
        }
        if (hide == 2) {
            door.hide();
        }
        groups.get(hide).setVisible(false);
        groups.get(show).setVisible(true);
        if (show == 2) {
            door.show();
        } else if (show == 3) {
            fourthSide.changeWindow();
        }
    }

    public boolean isBoxQuestSolved() {
        return boxQuest.isOpen();
    }

    @Override
    public void render() {
        super.render();
        if (!isBoxQuestSolved()) {
            door.setAvailable(false);
        }
    }
}
