package com.codecool.scrabble.Controller;

import com.codecool.scrabble.Model.*;
import com.codecool.scrabble.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.LinkedList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/scrabble")

public class GameRestController {

    private GameController gameController;


    @Autowired
    public GameRestController(
            GameController gameController) {

        this.gameController = gameController;
    }

    @PostMapping
    public void postBoard(@RequestBody Cell[] cells) {
        for (Cell cell : cells) {
            System.out.println(cell.getCellIndex());
        }
    }

    @GetMapping(path = "/restart")
    public ResponseEntity<ResponseAfterMove> restart() {

        ResponseAfterMove response = gameController.restart();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/board")
    public ResponseEntity<ResponseAfterMove> postWord(@RequestBody Board board, User user) {
        ResponseAfterMove response = gameController.serveMove(board);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/draw")
    public ResponseEntity<ResponseAfterDrawing> drawLetters(@RequestParam(value = "draw", defaultValue = "7") int number) {
        ResponseAfterDrawing response = gameController.drawLetters(number);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/draw")
    public ResponseEntity<ResponseAfterDrawing> getBackLetters(@RequestBody Character[] letters) {
        ResponseAfterDrawing response = gameController.drawLetters(letters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
