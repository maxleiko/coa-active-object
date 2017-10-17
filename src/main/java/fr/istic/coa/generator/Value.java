package fr.istic.coa.generator;

public class Value {

	private volatile Integer id = -1;
	private Integer value;

	public void setValue(Integer value) {
		this.id++;
		this.value = value;
	}

	public Integer id() {
		return this.id;
	}

	public Integer value() {
		return this.value;
	}
}
