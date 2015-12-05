package com.tpo.strava.gui.event;


import com.tpo.strava.gui.navigator.DashboardViewType;

public abstract class DashboardEvent {

    public static final class UserLoggedInEvent {
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
