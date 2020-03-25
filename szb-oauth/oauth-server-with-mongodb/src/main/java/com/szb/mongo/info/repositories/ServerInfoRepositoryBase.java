package com.szb.mongo.info.repositories;

import java.util.List;

import com.szb.mongo.info.domain.ServerInfo;

public interface ServerInfoRepositoryBase {

	ServerInfo findByUniqueKeys(String host, String port);
	List<ServerInfo> findByAppName(String appName);

}
