import Vue from 'vue'

const profile = Vue.resource('/profile{/id}')

export default {
    get: id => profile.get({id}),
    changeSubscription: channelId => Vue.http.post(`/profile/change-subscription/${channelId}`),
    subscriberList: channelId => Vue.http.get(`/profile/get-subscribers/${channelId}`),
    changeSubscriptionStatus: subscriberId => Vue.http.post(`/profile/change-status/${subscriberId}`),
    changeContact: receiverId => Vue.http.post(`/profile/change-contacts/${receiverId}`),
    contactList: senderId => Vue.http.get(`/profile/get-contacts/${senderId}`),
    changeContactStatus: receiverId => Vue.http.post(`/profile/change-contact-status/${receiverId}`)
}