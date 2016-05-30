package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Order;

public class OrderComparator implements Comparator<Order> {

	@Override
	public int compare(Order arg0, Order arg1) {
		return arg1.getCreatedOn().compareTo(arg0.getCreatedOn());
	}

}
