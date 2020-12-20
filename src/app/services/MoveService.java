package app.services;

import app.database.MSSQLDatabase;
import app.entity.Address;
import app.entity.Move;
import app.services.common.LogService;
import javafx.concurrent.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoveService {
    private Move move;
    private List<Move> moveList = new ArrayList<>();

    public MoveService() {
        init();
    }

    private void init() {
        try {
            moveList = MSSQLDatabase.getInstance().getAllMove();
        } catch (Exception e) {
            LogService.error(e.getMessage());
        }
    }

    public Move createMove(Move move) {
        try {
            if (move.getId() > 0 && moveList.stream().anyMatch(a -> a.getId() == move.getId())) {
                return move;
            }
            move.setId(moveList.size() + 1);
            ServiceFactory.getHouseHoldService().moveHouseHold(move);
            MSSQLDatabase.getInstance().insertMove(move);
            moveList.add(move);

            return move;
        } catch (Exception e) {
            LogService.error(e.getMessage());
            return null;
        }
    }


    //Test
    public static void main(String[] args) {
        ServiceFactory.Init();

        MoveService moveService = ServiceFactory.getMoveService();
        Move move = new Move();
        move.setIdHouseHold(1);
        move.setIdOldAddress(1);
        move.setIdNewAddress(5);
        move.setMovingDate(new Date());
        move.setIdHouseHold(1);
        move.setType(1);

        moveService.createMove(move);
    }
}
