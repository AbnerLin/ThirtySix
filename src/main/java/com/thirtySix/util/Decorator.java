package com.thirtySix.util;

public enum Decorator {

	TABLE("餐桌"), TREE("樹"), BAR("櫃台"), WC("廁所"), KITCHEN("廚房");

	private String desc;

	Decorator(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return this.desc;
	}
}
