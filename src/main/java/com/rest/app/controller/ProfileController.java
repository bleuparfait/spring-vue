package com.rest.app.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.rest.app.domain.User;
import com.rest.app.domain.UserContact;
import com.rest.app.domain.UserSubscription;
import com.rest.app.domain.Views;
import com.rest.app.service.ProfileService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    @JsonView(Views.FullProfile.class)
    public User get(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping("change-subscription/{channelId}")
    @JsonView(Views.FullProfile.class)
    public User changeSubscription(@AuthenticationPrincipal User subscriber,
                                   @PathVariable("channelId") User channel) {
        if (subscriber.equals(channel)) {
            return channel;
        } else {
            return profileService.changeSubscription(channel, subscriber);
        }
    }

    @GetMapping("get-subscribers/{channelId}")
    @JsonView(Views.IdName.class)
    public List<UserSubscription> subscribers(@PathVariable("channelId") User channel) {
        return profileService.getSubscribers(channel);
    }

    @PostMapping("change-status/{subscriberId}")
    @JsonView(Views.IdName.class)
    public UserSubscription changeSubscriptionStatus(@AuthenticationPrincipal User channel, @PathVariable("subscriberId") User subscriber) {
        return profileService.changeSubscriptionStatus(channel, subscriber);
    }

    @PostMapping("change-contacts/{receiverId}")
    @JsonView(Views.FullProfile.class)
    @Transactional
    public User changeContacts(@AuthenticationPrincipal User sender, @PathVariable("receiverId") User receiver) {
        if (sender.equals(receiver)) {
            return sender;
        } else
        return profileService.changeContacts(sender, receiver);
    }

    @GetMapping("get-contacts/{senderId}")
    @JsonView(Views.IdName.class)
    public Set<UserContact> getContacts(@PathVariable("senderId") User sender) {
        return profileService.getContacts(sender);
    }

    @PostMapping("change-contact-status/{senderId}")
    @JsonView(Views.IdName.class)
    public UserContact changeContactStatus(@AuthenticationPrincipal User receiver, @PathVariable("senderId") User sender) {
        return profileService.changeContactStatus(receiver, sender);
    }

}
