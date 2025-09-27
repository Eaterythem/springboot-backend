package io.eaterythem.eaterythem.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eaterythem.eaterythem.dto.FriendshipDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.FriendshipService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {

    FriendshipService friendshipService;

    @PutMapping("/addFriend/{userId}")
    public FriendshipDTO addAcceptFriend(@PathVariable Integer userId, @CurrentUser UserPrincipal user) {

        FriendshipDTO friendshipDTO = friendshipService.createOrAcceptFriendship(userId, user.getUserId());

        return friendshipDTO;
    }

    @PatchMapping("/{id}")
    public FriendshipDTO updateFriendship(@PathVariable Integer id, @CurrentUser UserPrincipal user,
            FriendshipDTO friendshipDTO) {

        FriendshipDTO newfriendshipDTO = friendshipService.updateFriendship(id, user.getUserId(), friendshipDTO);

        return newfriendshipDTO;
    }

    @DeleteMapping("/{id}")
    public FriendshipDTO deleteFriendship(@PathVariable Integer id, @CurrentUser UserPrincipal user){

        FriendshipDTO friendshipDTO = friendshipService.deleteFriendship(id, user.getUserId());

        return friendshipDTO;
    }

}
