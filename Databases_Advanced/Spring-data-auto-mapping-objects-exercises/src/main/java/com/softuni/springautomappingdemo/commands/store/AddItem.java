package com.softuni.springautomappingdemo.commands.store;

import com.softuni.springautomappingdemo.commands.Command;
import com.softuni.springautomappingdemo.domain.dtos.GameTitleDto;
import com.softuni.springautomappingdemo.services.GameService;
import com.softuni.springautomappingdemo.services.StoreService;
import com.softuni.springautomappingdemo.services.UserService;

import static com.softuni.springautomappingdemo.constants.Messages.*;

public class AddItem extends Command {
    private final static int INPUT_SIZE = 2;
    private final static int INDEX_TITLE = 1;

    public AddItem(UserService userService, GameService gameService, StoreService storeService) {
        super(userService, gameService, storeService);
    }

    @Override
    public String execute(String... args) {

        if (args == null || args.length < INPUT_SIZE){
            return BAD_INPUT;
        }

        if (!storeService.isLoggedUser()){
            return USER_REQUIRED;
        }

        if(!gameService.existsGameByTitleLike(args[INDEX_TITLE])){
            return GAME_NOT_FOUND;
        }

        GameTitleDto gameTitleDto = new GameTitleDto(args[INDEX_TITLE]);

        return storeService.addItem(gameTitleDto);
    }
}
