package com.tpo.strava.service.strava.gui.event;


import com.tpo.strava.service.strava.gui.DashboardViewType;

/*
 * Event bus events used in Dashboard are listed here as inner classes.
 */
public abstract class DashboardEvent {

    public static final class UserLoginRequestedEvent {
//        private final String userName, password;

        public UserLoginRequestedEvent() {
        }

//        public UserLoginRequestedEvent(final String userName,
//                final String password) {
//            this.userName = userName;
//            this.password = password;
//        }
//
//        public String getUserName() {
//            return userName;
//        }
//
//        public String getPassword() {
//            return password;
//        }
    }

    public static class UserLoggedOutEvent {

    }

    public static class ProfileUpdatedEvent {
    }

    public static final class PostViewChangeEvent {
        private final DashboardViewType view;

        public PostViewChangeEvent(final DashboardViewType view) {
            this.view = view;
        }

        public DashboardViewType getView() {
            return view;
        }
    }

}
