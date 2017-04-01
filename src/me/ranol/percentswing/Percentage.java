package me.ranol.percentswing;

import java.awt.*;

public class Percentage {
	public static final Percentage EMPTY = new Percentage(0, 0, 0, 0);
	public static final Percentage MAX = new Percentage(0, 0, 100, 100);

	public enum Type {
		X,
		Y,
		WIDTH,
		HEIGHT
	}

	public static final class Builder {
		private double x = 0;
		private double y = 0;
		private double w = 0;
		private double h = 0;

		private Builder() {
		}

		public Builder x(double d) {
			this.x = d;
			return this;
		}

		public Builder y(double d) {
			this.y = d;
			return this;
		}

		public Builder width(double d) {
			this.w = d;
			return this;
		}

		public Builder height(double d) {
			this.h = d;
			return this;
		}

		public Percentage build() {
			return new Percentage(x, y, w, h);
		}
	}

	private double xPercentage;
	private double yPercentage;
	private double widthPercentage;
	private double heightPercentage;

	public static Builder builder() {
		return new Builder();
	}

	public Percentage(double xPercentage, double yPercentage, double widthPercentage, double heightPercentage) {
		this.xPercentage = xPercentage;
		this.yPercentage = yPercentage;
		this.widthPercentage = widthPercentage;
		this.heightPercentage = heightPercentage;
	}

	public double getPercentOf(Type type) {
		switch (type) {
			case X:
				return xPercentage;
			case Y:
				return yPercentage;
			case WIDTH:
				return widthPercentage;
			case HEIGHT:
				return heightPercentage;
			default:
				return Double.NaN;
		}
	}

	public int from(Type type, Dimension dim, double max, Insets inset) {
		switch (type) {
			case X:
				return (int) (xPercentage * (dim.getWidth() - inset.left - inset.right) / max);
			case Y:
				return (int) (yPercentage * (dim.getHeight() - inset.top - inset.bottom) / max);
			case WIDTH:
				return (int) (widthPercentage * (dim.getWidth() - inset.left - inset.right) / max);
			case HEIGHT:
				return (int) (heightPercentage * (dim.getHeight() - inset.top - inset.bottom) / max);
			default:
				return 0;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Percentage that = (Percentage) o;

		if (Double.compare(that.xPercentage, xPercentage) != 0) {
			return false;
		}
		if (Double.compare(that.yPercentage, yPercentage) != 0) {
			return false;
		}
		if (Double.compare(that.widthPercentage, widthPercentage) != 0) {
			return false;
		}
		return Double.compare(that.heightPercentage, heightPercentage) == 0;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(xPercentage);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yPercentage);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(widthPercentage);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(heightPercentage);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Percentage{" + "x=" + xPercentage + ", y=" + yPercentage + ", width=" + widthPercentage + ", height=" + heightPercentage + '}';
	}
}
