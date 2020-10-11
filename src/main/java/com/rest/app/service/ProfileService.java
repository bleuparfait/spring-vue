package com.rest.app.service;

import com.rest.app.domain.User;
import com.rest.app.domain.UserContact;
import com.rest.app.domain.UserSubscription;
import com.rest.app.repo.UserContactRepo;
import com.rest.app.repo.UserDetailsRepo;
import com.rest.app.repo.UserSubscriptionRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserDetailsRepo userDetailsRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;
    private final UserContactRepo userContactRepo;

    public ProfileService(UserDetailsRepo userDetailsRepo, UserSubscriptionRepo userSubscriptionRepo, UserContactRepo userContactRepo) {
        this.userDetailsRepo = userDetailsRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
        this.userContactRepo = userContactRepo;
    }

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subscriptions = channel.getSubscribers()
                .stream()
                .filter(subscription -> subscription.getSubscriber().equals(subscriber))
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
            channel.getSubscribers().removeAll(subscriptions);
        }
        return userDetailsRepo.save(channel);
    }

    public List<UserSubscription> getSubscribers(User channel) {
        return userSubscriptionRepo.findByChannel(channel);
    }

    public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
        UserSubscription subscription = userSubscriptionRepo.findByChannelAndSubscriber(channel, subscriber);
        subscription.setActive(!subscription.isActive());
        return userSubscriptionRepo.save(subscription);
    }

    public User changeContacts(User sender, User receiver) {
//        User userFromDb = userDetailsRepo.findById(sender.getId()).orElse(new User());
//        List<User> contacts = userFromDb
//                .getContacts()
//                .stream()
//                .filter(c -> c.equals(receiver))
//                .collect(Collectors.toList());
//        if (contacts.isEmpty()) {
//            userFromDb.getContacts().add(receiver);
//        } else {
//            userFromDb.getContacts().removeAll(contacts);
//        }
        User senderFromDb = userDetailsRepo.findById(sender.getId()).orElseThrow();
        List<UserContact> contacts = senderFromDb.getContacts()
                .stream()
                .filter(c -> c.getReceiver().equals(receiver))
                .collect(Collectors.toList());

        if (contacts.isEmpty()) {
            senderFromDb.getContacts().add(new UserContact(senderFromDb, receiver));
        } else {
            senderFromDb.getContacts().removeAll(contacts);
        }
        return userDetailsRepo.save(senderFromDb);
    }

    public Set<UserContact> getContacts(User user) {
        Set<UserContact> contacts = new HashSet<>();
        contacts.addAll(userContactRepo.findByReceiver(user));
        contacts.addAll(userContactRepo.findBySender(user));
        return contacts;
    }

    public UserContact changeContactStatus(User receiver, User sender) {
        UserContact contact = userContactRepo.findBySenderAndReceiver(sender, receiver);
        contact.setActive(!contact.isActive());
        return userContactRepo.save(contact);
    }

}
