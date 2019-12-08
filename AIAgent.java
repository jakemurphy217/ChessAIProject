import java.util.*;

public class AIAgent {
    Random rand;

    public AIAgent() {
        rand = new Random();
    }

  /* The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a random number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent. */


    public Move randomMove(Stack possibilities) {

        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent randomly selected move : " + moveID);
        for (int i = 1; i < (possibilities.size() - (moveID)); i++) {
            possibilities.pop();
        }
        Move selectedMove = (Move) possibilities.pop();
        return selectedMove;
    }

    // NextLevelDeep is one level deep and will calculate all the best possible moves from the possible stack with taking the
    // the blacks next move into consideration
    ///////// Start of one level deep /////////

    public Move nextBestMove(Stack whitePossibilities, Stack blackpossibilities) {


        Stack whiteStack = (Stack) whitePossibilities.clone();
        Stack blackStack = (Stack) blackpossibilities.clone();
        Move whiteMove, bestMove, attackingMove;

        Square blackPosition;
        int gameScore;
        int chosenPiece = 0;
        attackingMove = null;


        while (!whitePossibilities.empty()) {
            whiteMove = (Move) whitePossibilities.pop();
            bestMove = whiteMove;


            //giving the centre of the board 1 point
            if ((bestMove.getStart().getYC() < bestMove.getLanding().getYC()) &&
                       (bestMove.getLanding().getXC() == 3) && (bestMove.getLanding().getYC() == 3)
                    || (bestMove.getLanding().getXC() == 4) && (bestMove.getLanding().getYC() == 3)

                    || (bestMove.getLanding().getXC() == 2) && (bestMove.getLanding().getXC() == 3)
                    || (bestMove.getLanding().getXC() == 5) && (bestMove.getLanding().getYC() == 3)

                    || (bestMove.getLanding().getXC() == 2) && (bestMove.getLanding().getYC() == 4)
                    || (bestMove.getLanding().getXC() == 5) && (bestMove.getLanding().getYC() == 4)

                    || (bestMove.getLanding().getXC() == 3) && (bestMove.getLanding().getYC() == 4)
                    || (bestMove.getLanding().getXC() == 4) && (bestMove.getLanding().getYC() == 4)) {

                gameScore = 1;

                //choosing the best move possible
                if (gameScore > chosenPiece) {
                    chosenPiece = gameScore;
                    attackingMove = bestMove;
                }
            }


            //now will be assigning the rest of the game pieces on the board with values
            //now that that the agent scores 1 point to get to the centre of the board pawns will be now
            //2 points instead of the international standard " 1 point for a pawn "

            //centre of the board is 1 point
            //Pawns 2 points
            //Knight and Bishop 3 points (international standard)
            //Rook 5 points (international standard)
            //Queen 9 points (international standard)
            //King 10 points (international standard)

            //so if the agent will try take the piece with the highest score in its available position
            //if the agent cannot take a piece it will try to get to the centre of the board to try gain an advantage
            // if neither of the situations are available the agent will make a random move.

            while (!blackStack.isEmpty()) {
                gameScore = 0;
                blackPosition = (Square) blackStack.pop();

                if ((bestMove.getLanding().getXC() == blackPosition.getXC()) && (bestMove.getLanding().getYC() == blackPosition.getYC() )){

                  if (blackPosition.getName().equals("BlackPawn")){
                    gameScore = 2;
                  }

                  else if (blackPosition.getName().equals("BlackKnight") || blackPosition.getName().equals("BlackBishop")){
                    gameScore = 3;
                  }

                  else if (blackPosition.getName().equals("BlackRook")){
                    gameScore = 5;
                  }

                  else if (blackPosition.getName().equals("BlackQueen")){
                    gameScore = 9;
                  }

                  else if (blackPosition.getName().equals("BlackKing")){
                    gameScore = 10;
                  }
                }
                //after values are assigned to eah piece the agent chooses the best score

              if (gameScore > chosenPiece){
                chosenPiece = gameScore;
                attackingMove = bestMove;
              }
            }

          //iterates through the stack to find best move and without this its main objective would be
          //the centre of the board
          blackStack = (Stack) blackpossibilities.clone();

        }

        //so i mentioned above earlier if the agent cant make a move bigger then 0 it will do a random move
        if(chosenPiece > 0){
          System.out.println("the AI agent NextBestMove is" + chosenPiece);
          return attackingMove;
        }
        return randomMove(whiteStack);
    }


    ///////// End of one level deep /////////

    public Move twoLevelsDeep(Stack possibilities) {
        Move selectedMove = new Move();
        return selectedMove;
    }
}
