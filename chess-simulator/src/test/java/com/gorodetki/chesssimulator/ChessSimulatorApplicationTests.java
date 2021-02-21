package com.gorodetki.chesssimulator;

import com.gorodetki.chesssimulator.services.impl.ChessBoardStateHandlingService;
import com.gorodetki.chesssimulator.services.impl.ChessGameSimulatorProcessor;
import com.gorodetki.chesssimulator.services.impl.ChessSpotsInitializer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static com.gorodetki.chesssimulator.models.pieces.PieceColor.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ActiveProfiles("dev")
class ChessSimulatorIntegrationTest {
    @Autowired
    ChessGameSimulatorProcessor chessGameSimulatorProcessor;
    @Autowired
    ChessSpotsInitializer chessSpotsInitializer;
    @Autowired
    ChessBoardStateHandlingService chessBoardStateHandlingService;


    @ParameterizedTest
    @MethodSource("getFilePathAndBoard")
    void shouldProcessAllMovesFromFileAndMoveAllChessmenOnBoard(String path, String board) {
        chessGameSimulatorProcessor.startGameSimulation(path);
        assertThat(chessGameSimulatorProcessor.getChessBoard().toString()).isEqualTo(board);
        chessBoardStateHandlingService.setMoveOwner(WHITE);
        chessSpotsInitializer.resetBoard();
    }


    private static Stream<Arguments> getFilePathAndBoard() {
        return Stream.of(
                Arguments.of("src/test/resources/movesUntilCheck.txt",

                        "   a b c d e f g h\n" +
                                "   ----------------\n" +
                                "1| R N B . K . N R |1\n" +
                                "2| P P P P . P P P |2\n" +
                                "3| . . . . . . . . |3\n" +
                                "4| . . B . P . . . |4\n" +
                                "5| . . . . p . . . |5\n" +
                                "6| . . n p . . . . |6\n" +
                                "7| p p p . . Q p p |7\n" +
                                "8| r . b q k b n r |8\n" +
                                "   ----------------\n" +
                                "   a b c d e f g h\n"),

                Arguments.of("src/test/resources/onlyValidMovesNotCheckState.txt",

                        "   a b c d e f g h\n" +
                                "   ----------------\n" +
                                "1| R . B Q K B N . |1\n" +
                                "2| P P P P . P P R |2\n" +
                                "3| . . N . . . . P |3\n" +
                                "4| . . . . P . . . |4\n" +
                                "5| . . . . p . . . |5\n" +
                                "6| . . . p b . . . |6\n" +
                                "7| p p p . . p p p |7\n" +
                                "8| r n . q k b n r |8\n" +
                                "   ----------------\n" +
                                "   a b c d e f g h\n"),

                Arguments.of("src/test/resources/validAndInvalidMoves.txt",
                        "   a b c d e f g h\n" +
                                "   ----------------\n" +
                                "1| R N B Q K B N . |1\n" +
                                "2| P . P P . P P R |2\n" +
                                "3| . P . . . . . P |3\n" +
                                "4| . . . . P . . . |4\n" +
                                "5| p . . . p . . . |5\n" +
                                "6| . . . p b . . . |6\n" +
                                "7| . p p . . p p p |7\n" +
                                "8| r n . q k b n r |8\n" +
                                "   ----------------\n" +
                                "   a b c d e f g h\n"));
    }

}
