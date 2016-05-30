package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Notification;

public class NotificationComparator implements Comparator<Notification>{

	@Override
	public int compare(Notification r1, Notification r2) {
		return r2.getCreationDate().compareTo(r1.getCreationDate());
	}

}
