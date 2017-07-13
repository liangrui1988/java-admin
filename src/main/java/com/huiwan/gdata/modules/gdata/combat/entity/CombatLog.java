package com.huiwan.gdata.modules.gdata.combat.entity;

public class CombatLog {

	private Long id;
	private String dt;
	private String uuid;
	private Integer serverId;
	private String server;
	private String fileName;
	private String file;
	private String cont;
	private String add_hp;
	private String dungeon_id;
	
	private long pageId;
	
	
	
	

	


	public long getPageId() {
		return pageId;
	}

	public void setPageId(long pageId) {
		this.pageId = pageId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getAdd_hp() {
		return add_hp;
	}

	public void setAdd_hp(String add_hp) {
		this.add_hp = add_hp;
	}

	public String getDungeon_id() {
		return dungeon_id;
	}

	public void setDungeon_id(String dungeon_id) {
		this.dungeon_id = dungeon_id;
	}

}
