package com.aaread.redis.jedis;

public class JedisSetting {
	private String name;
	private int dataType;
	private String value;
	private int flag;

	@SuppressWarnings("unused")
	private JedisSetting() {
	}

	public JedisSetting(String propName, int dataType) {
		this.name = propName;
		this.dataType = dataType;
	}

	public int getDataType() {
		return dataType;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public long getLongValue() {
		return Long.parseLong(value);
	}

	public int getIntValue() {
		return Integer.parseInt(value);
	}

	public boolean getBooleanValue() {
		if ("true".equals(value)) {
			return true;
		} else {
			return false;
		}
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
