package com.huiwan.gdata.modules.gdata.combat.entity;

public class CombatAttr {

	public CombatAttr() {
		// TODO Auto-generated constructor stub
	}
	
	private String time;

	private String uuid;
	private String cont; //json值
	private String contDif; //json值
	private String name; //名称
	private String lv;//等级
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContDif() {
		return contDif;
	}
	public void setContDif(String contDif) {
		this.contDif = contDif;
	}
	@Override
	public String toString() {
		return "CombatAttr [time=" + time + ", uuid=" + uuid + ", cont=" + cont + ", name=" + name + ", lv=" + lv + "]";
	}
	
	
	
	
	

}
