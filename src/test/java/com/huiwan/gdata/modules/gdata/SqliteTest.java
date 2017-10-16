package com.huiwan.gdata.modules.gdata;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.comm.BaseServiceTest;
import com.huiwan.gdata.modules.gdata.combat.entity.SqliteBean;
import com.huiwan.gdata.modules.gdata.combat.service.SqliteService;

public class SqliteTest extends BaseServiceTest {

	public SqliteTest() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private SqliteService sqliteService;

	@Test
	public void get() {
		List<SqliteBean> bens = sqliteService.gett();
		printJson(bens);

	}

}
