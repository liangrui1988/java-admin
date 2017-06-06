package com.huiwan.gdata.modules.sys.web.interceptor;

public class Verification {
	
//	@SuppressWarnings("unchecked")
//	public boolean loginVerify(TokenParm tp){
////		if(!Env.isProd()) {
////			// 非生产环境不检验token
////			return true;
////		}
//		long curt = DateUtil.getCurrentSecond();
//		Map<String, Object> nvps = new HashMap<String, Object>();
//		nvps.put("userid", tp.getPlayerId());
//		nvps.put("token", tp.getToken());
//		nvps.put("clientip", tp.getLoginIp());
//		nvps.put("appid", tp.getAppid());
//		nvps.put("serverid", SERVER_ID);
//		nvps.put("servertime", curt);
//		nvps.put("key", DigestUtils.md5Hex(1033 + SERVER_KEY	+ curt));
//		try {
//			//logger.warn("======request userid:{},param:{}",KugouUtil.toJson(nvps) );
//			Map map = httpService.postBody(URL_VERIFY_TOKEN, KugouUtil.toJson(nvps).getBytes(Consts.UTF_8), Map.class,null).get();
//			logger.warn("=========" + map + "==");
//			if(map!=null && map.size()>0){
//				if ((Integer)map.get("status") == 1) {
//					Map<String,Object> result = (Map<String, Object>) map.get("data");
//					if(1==(Integer)result.get("right")){
//						return true;
//					}
//				} else {
//					logger.error("大酷狗返回:{}", map);
//					return false;
//				}
//			} else {
//				logger.error("verifyToken - http error: {}  params:{}",map, nvps);
//			}
//		} catch (Exception e) {
//			logger.error("verifyToken-", e);
//		}
//		return false;
//	}

}
